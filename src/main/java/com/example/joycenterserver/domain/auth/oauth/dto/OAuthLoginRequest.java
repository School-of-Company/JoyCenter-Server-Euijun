package com.example.joycenterserver.domain.auth.oauth.dto;

import com.example.joycenterserver.domain.auth.oauth.type.OAuthType;

public record OAuthLoginRequest(
        OAuthType provider,
        String code
) {
}
