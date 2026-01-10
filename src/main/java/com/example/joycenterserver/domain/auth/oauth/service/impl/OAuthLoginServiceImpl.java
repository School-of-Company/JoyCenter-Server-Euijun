package com.example.joycenterserver.domain.auth.oauth.service.impl;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthLoginRequest;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import com.example.joycenterserver.domain.auth.oauth.provider.OAuthProvider;
import com.example.joycenterserver.domain.auth.oauth.service.OAuthLoginService;
import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.service.MemberGetOrCreateService;
import com.example.joycenterserver.global.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private static final Logger log = LoggerFactory.getLogger(OAuthLoginServiceImpl.class);

    private final List<OAuthProvider> providers;
    private final JwtProvider jwtProvider;
    private final MemberGetOrCreateService memberGetOrCreateService;

    public OAuthLoginServiceImpl(
            List<OAuthProvider> providers,
            JwtProvider jwtProvider,
            MemberGetOrCreateService memberGetOrCreateService
    ) {
        this.providers = providers;
        this.jwtProvider = jwtProvider;
        this.memberGetOrCreateService = memberGetOrCreateService;
    }

    @Override
    public TokenResponse login(OAuthLoginRequest request) {
        try {
            log.info("[OAuthLogin] login start. provider={}, code={}",
                    request.provider(),
                    request.code()
            );

            OAuthProvider provider = providers.stream()
                    .filter(p -> p.getType() == request.provider())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "OAuthProvider not found: " + request.provider()
                    ));

            log.info("[OAuthLogin] provider resolved: {}", provider.getClass().getSimpleName());

            OAuthUserInfo userInfo = provider.authenticate(request.code());
            log.info("[OAuthLogin] userInfo received. email={}, oauthId={}",
                    userInfo.email(),
                    userInfo.oauthId()
            );

            Member member = memberGetOrCreateService.getOrCreate(userInfo.email());
            log.info("[OAuthLogin] member resolved. memberId={}, email={}",
                    member.getMemberId(),
                    member.getEmail()
            );

            TokenResponse tokenResponse = jwtProvider.issueToken(member.getMemberId());
            log.info("[OAuthLogin] token issued successfully. memberId={}",
                    member.getMemberId()
            );

            return tokenResponse;

        } catch (Exception e) {
            log.error("[OAuthLogin] login failed", e);
            throw e;
        }
    }
}
