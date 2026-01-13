package com.example.joycenterserver.domain.auth.oauth.client.kakao;

import com.example.joycenterserver.domain.auth.oauth.client.OAuthClient;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class KakaoOAuthClient implements OAuthClient {

    private static final Logger log = LoggerFactory.getLogger(KakaoOAuthClient.class);

    private final WebClient webClient;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    public KakaoOAuthClient(
            @Value("${oauth.kakao.client-id}") String clientId,
            @Value("${oauth.kakao.client-secret}") String clientSecret,
            @Value("${oauth.kakao.redirect-uri}") String redirectUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.webClient = WebClient.create();
    }

    @Override
    public OAuthUserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);

        Map<String, Object> response = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        log.info("[KakaoOAuth] user info response = {}", response);

        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return new OAuthUserInfo(
                String.valueOf(response.get("id")),
                (String) kakaoAccount.get("email"),
                (String) profile.get("nickname")
        );
    }

    private String getAccessToken(String code) {
        log.info("""
[KakaoOAuth] token request
clientId={}
redirectUri={}
clientSecretPresent={}
code={}
""",
                clientId,
                redirectUri,
                clientSecret != null && !clientSecret.isBlank(),
                code
        );

        Map<String, Object> response = webClient.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8")
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("redirect_uri", redirectUri)
                        .with("code", code)
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        log.info("[KakaoOAuth] token response = {}", response);

        return (String) response.get("access_token");
    }
}
