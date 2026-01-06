package com.example.joycenterserver.domain.auth.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
