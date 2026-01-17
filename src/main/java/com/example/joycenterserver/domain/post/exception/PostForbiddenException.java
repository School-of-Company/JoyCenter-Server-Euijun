package com.example.joycenterserver.domain.post.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class PostForbiddenException extends GlobalException {

    public PostForbiddenException() {
        super(ErrorCode.POST_FORBIDDEN);
    }
}
