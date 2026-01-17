package com.example.joycenterserver.domain.attachment.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class NotFoundAttachmentException extends GlobalException {
    public NotFoundAttachmentException() {
        super(ErrorCode.NOT_FOUND_ATTACHMENT);
    }
}
