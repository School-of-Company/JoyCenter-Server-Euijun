package com.example.joycenterserver.domain.post.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class InvalidPostContentException extends GlobalException {

    public InvalidPostContentException() {
        super(ErrorCode.INVALID_POST_CONTENT);
    }
}
