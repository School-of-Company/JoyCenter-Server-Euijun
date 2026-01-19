package com.example.joycenterserver.domain.post.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class InvalidPostRequestException extends GlobalException {

    public InvalidPostRequestException() {
        super(ErrorCode.INVALID_POST_REQUEST);
    }
}
