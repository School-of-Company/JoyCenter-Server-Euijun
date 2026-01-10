package com.example.joycenterserver.domain.auth.oauth.provider;

import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import com.example.joycenterserver.domain.auth.oauth.type.OAuthType;

public interface OAuthProvider {
    OAuthType getType();
    OAuthUserInfo authenticate(String code);
}
