package com.example.joycenterserver.domain.auth.oauth.dto;

public record OAuthLoginRequest(
        String provider,
        String code,
        String redirectUri,
        String clientId,
        String clientSecret
) {}
