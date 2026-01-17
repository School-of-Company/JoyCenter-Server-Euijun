package com.example.joycenterserver.domain.attachment.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class AttachmentForbiddenException extends GlobalException {
    public AttachmentForbiddenException() {
        super(ErrorCode.ATTACHMENT_FORBIDDEN);
    }
}
