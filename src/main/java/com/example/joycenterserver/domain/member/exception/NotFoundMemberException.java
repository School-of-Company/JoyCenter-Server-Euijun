package com.example.joycenterserver.domain.member.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class NotFoundMemberException extends GlobalException {
    public NotFoundMemberException() {
        super(ErrorCode.NOT_FOUND_MEMBER);
    }
}
