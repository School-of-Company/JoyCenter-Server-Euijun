package com.example.joycenterserver.domain.auth.oauth.service.impl;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthLoginRequest;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import com.example.joycenterserver.domain.auth.oauth.exception.UnsupportedOAuthProviderException;
import com.example.joycenterserver.domain.auth.oauth.provider.OAuthProvider;
import com.example.joycenterserver.domain.auth.oauth.service.OAuthLoginService;
import com.example.joycenterserver.domain.auth.oauth.type.OAuthType;
import com.example.joycenterserver.domain.auth.service.AuthTokenService;
import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.service.MemberGetOrCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private final List<OAuthProvider> providers;
    private final MemberGetOrCreateService memberGetOrCreateService;
    private final AuthTokenService authTokenService;

    @Override
    public TokenResponse login(OAuthLoginRequest request) {

        OAuthType type = OAuthType.valueOf(request.provider().toUpperCase());

        OAuthProvider provider = providers.stream()
                .filter(p -> p.getType() == type)
                .findFirst()
                .orElseThrow(UnsupportedOAuthProviderException::new);

        OAuthUserInfo userInfo = provider.authenticate(request.code());

        Member member = memberGetOrCreateService.getOrCreate(
                userInfo.email(),
                userInfo.name()
        );

        return authTokenService.issue(member.getMemberId());
    }
}
