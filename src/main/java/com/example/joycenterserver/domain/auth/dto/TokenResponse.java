package com.example.joycenterserver.domain.auth.dto;

import java.util.Date;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        Date accessTokenExpiresAt,
        Date refreshTokenExpiresAt
) {
}
