package com.example.joycenterserver.domain.auth.controller;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;
import com.example.joycenterserver.domain.auth.service.TokenReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenReissueService tokenReissueService;

    @PatchMapping("/api/auth/reissue")
    public ResponseEntity<TokenResponse> reissue(
            @RequestHeader("Refresh-Token") String refreshToken
    ) {
        return ResponseEntity.ok(tokenReissueService.reissue(refreshToken));
    }
}
