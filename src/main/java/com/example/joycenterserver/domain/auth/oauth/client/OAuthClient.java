package com.example.joycenterserver.domain.auth.oauth.client;

import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;

public interface OAuthClient {
    OAuthUserInfo getUserInfo(String code);
}
