package com.example.joycenterserver.domain.auth.service;

import com.example.joycenterserver.domain.auth.dto.TokenResponse;

public interface TokenReissueService {

    TokenResponse reissue(String refreshToken);
}
