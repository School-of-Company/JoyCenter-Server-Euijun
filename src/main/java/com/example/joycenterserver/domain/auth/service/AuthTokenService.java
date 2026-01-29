package com.example.joycenterserver.domain.auth.service;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.entity.RefreshToken;
import com.example.joycenterserver.domain.auth.repository.RefreshTokenRepository;
import com.example.joycenterserver.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthTokenService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse issue(Long memberId) {
        TokenResponse token = jwtProvider.issueToken(memberId);
        refreshTokenRepository.save(
                new RefreshToken(memberId, token.refreshToken())
        );
        return token;
    }

    public TokenResponse reissue(String refreshToken) {
        jwtProvider.validateToken(refreshToken);

        RefreshToken saved = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow();

        Long memberId = jwtProvider.getMemberId(refreshToken);

        refreshTokenRepository.deleteById(saved.getMemberId());

        TokenResponse newToken = jwtProvider.issueToken(memberId);
        refreshTokenRepository.save(
                new RefreshToken(memberId, newToken.refreshToken())
        );

        return newToken;
    }

    public void logout(Long memberId) {
        refreshTokenRepository.deleteById(memberId);
    }
}
