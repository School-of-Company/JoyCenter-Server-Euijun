package com.example.joycenterserver.domain.auth.oauth.client.google;

import com.example.joycenterserver.domain.auth.oauth.client.OAuthClient;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Component
public class GoogleOAuthClient implements OAuthClient {

    private final WebClient webClient = WebClient.create();
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    public GoogleOAuthClient(
            @Value("${oauth.google.client-id}") String clientId,
            @Value("${oauth.google.client-secret}") String clientSecret,
            @Value("${oauth.google.redirect-uri}") String redirectUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    @Override
    public OAuthUserInfo getUserInfo(String code) {
        log.info("[GoogleOAuth] token request");

        String accessToken = getAccessToken(code);

        Map<String, Object> response = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        log.info("[GoogleOAuth] user info response = {}", response);

        return new OAuthUserInfo(
                (String) response.get("id"),
                (String) response.get("email"),
                (String) response.get("name")
        );
    }

    private String getAccessToken(String code) {
        Map<String, Object> response = webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue(
                        "grant_type=authorization_code" +
                                "&client_id=" + clientId +
                                "&client_secret=" + clientSecret +
                                "&redirect_uri=" + redirectUri +
                                "&code=" + code
                )
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        log.info("[GoogleOAuth] token response = {}", response);

        return (String) response.get("access_token");
    }
}
