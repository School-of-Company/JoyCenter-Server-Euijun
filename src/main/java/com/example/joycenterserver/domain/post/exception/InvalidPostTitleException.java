package com.example.joycenterserver.domain.post.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class InvalidPostTitleException extends GlobalException {

    public InvalidPostTitleException() {
        super(ErrorCode.INVALID_POST_TITLE);
    }
}
