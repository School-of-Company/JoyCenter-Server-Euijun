package com.example.joycenterserver.domain.auth.oauth.dto;

public record OAuthUserInfo(
        String oauthId,
        String email,
        String name
) {
}
