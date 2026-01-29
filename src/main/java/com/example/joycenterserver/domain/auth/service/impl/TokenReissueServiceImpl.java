package com.example.joycenterserver.domain.auth.service.impl;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.service.AuthTokenService;
import com.example.joycenterserver.domain.auth.service.TokenReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissueServiceImpl implements TokenReissueService {

    private final AuthTokenService authTokenService;

    @Override
    public TokenResponse reissue(String refreshToken) {
        return authTokenService.reissue(refreshToken);
    }
}
