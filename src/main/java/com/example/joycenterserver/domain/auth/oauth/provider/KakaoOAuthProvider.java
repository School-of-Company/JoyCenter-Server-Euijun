package com.example.joycenterserver.domain.auth.oauth.provider;

import com.example.joycenterserver.domain.auth.oauth.client.kakao.KakaoOAuthClient;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import com.example.joycenterserver.domain.auth.oauth.type.OAuthType;
import org.springframework.stereotype.Component;

@Component
public class KakaoOAuthProvider implements OAuthProvider {

    private final KakaoOAuthClient kakaoOAuthClient;

    public KakaoOAuthProvider(KakaoOAuthClient kakaoOAuthClient) {
        this.kakaoOAuthClient = kakaoOAuthClient;
    }

    @Override
    public OAuthType getType() {
        return OAuthType.KAKAO;
    }

    @Override
    public OAuthUserInfo authenticate(String code) {
        return kakaoOAuthClient.getUserInfo(code);
    }
}
