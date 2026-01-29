package com.example.joycenterserver.domain.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "joycenter_refresh_token", timeToLive = 1209600)
public class RefreshToken {

    @Id
    private Long memberId;

    @Indexed
    private String token;

    protected RefreshToken() {}

    public RefreshToken(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getToken() {
        return token;
    }
}
