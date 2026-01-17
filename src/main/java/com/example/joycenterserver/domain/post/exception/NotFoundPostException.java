package com.example.joycenterserver.domain.post.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class NotFoundPostException extends GlobalException {
    public NotFoundPostException() {
        super(ErrorCode.NOT_FOUND_POST);
    }
}
