package com.example.joycenterserver.domain.auth.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class ExpiredTokenException extends GlobalException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
