package com.example.joycenterserver.global.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private long accessTokenExpireTime;
    private long refreshTokenExpireTime;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setAccessTokenExpireTime(long accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public void setRefreshTokenExpireTime(long refreshTokenExpireTime) {
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }
}
