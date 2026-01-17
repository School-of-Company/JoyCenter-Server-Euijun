package com.example.joycenterserver.domain.attachment.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class InvalidAttachmentFileTypeException extends GlobalException {
    public InvalidAttachmentFileTypeException() {
        super(ErrorCode.INVALID_ATTACHMENT_FILE_TYPE);
    }
}
