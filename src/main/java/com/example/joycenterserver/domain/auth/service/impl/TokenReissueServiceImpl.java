package com.example.joycenterserver.domain.auth.service.impl;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.service.TokenReissueService;
import com.example.joycenterserver.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenReissueServiceImpl implements TokenReissueService {

    private final JwtProvider jwtProvider;

    @Override
    public TokenResponse reissue(String refreshToken) {
        jwtProvider.validateToken(refreshToken);

        Long memberId = jwtProvider.getMemberId(refreshToken);

        return jwtProvider.issueToken(memberId);
    }
}
