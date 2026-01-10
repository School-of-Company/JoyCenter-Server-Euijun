package com.example.joycenterserver.domain.auth.oauth.service;

import com.example.joycenterserver.domain.auth.oauth.dto.OAuthLoginRequest;
import com.example.joycenterserver.domain.auth.dto.TokenResponse;

public interface OAuthLoginService {
    TokenResponse login(OAuthLoginRequest request);
}
