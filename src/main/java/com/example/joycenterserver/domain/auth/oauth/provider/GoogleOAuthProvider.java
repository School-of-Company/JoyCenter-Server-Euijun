package com.example.joycenterserver.domain.auth.oauth.provider;

import com.example.joycenterserver.domain.auth.oauth.client.google.GoogleOAuthClient;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import com.example.joycenterserver.domain.auth.oauth.type.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthProvider implements OAuthProvider {

    private final GoogleOAuthClient googleOAuthClient;

    @Override
    public OAuthType getType() {
        return OAuthType.GOOGLE;
    }

    @Override
    public OAuthUserInfo authenticate(String code) {
        return googleOAuthClient.getUserInfo(code);
    }
}
