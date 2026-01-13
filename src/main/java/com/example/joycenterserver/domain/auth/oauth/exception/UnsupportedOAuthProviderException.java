package com.example.joycenterserver.domain.auth.oauth.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class UnsupportedOAuthProviderException extends GlobalException {

    public UnsupportedOAuthProviderException() {
        super(ErrorCode.UNSUPPORTED_OAUTH_PROVIDER);
    }
}
