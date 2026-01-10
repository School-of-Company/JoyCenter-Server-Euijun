package com.example.joycenterserver.domain.auth.controller;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.oauth.dto.OAuthLoginRequest;
import com.example.joycenterserver.domain.auth.oauth.service.OAuthLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/oauth")
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    public OAuthController(OAuthLoginService oAuthLoginService) {
        this.oAuthLoginService = oAuthLoginService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> oauthLogin(
            @RequestBody OAuthLoginRequest request
    ) {
        return ResponseEntity.ok(oAuthLoginService.login(request));
    }
}
