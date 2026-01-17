package com.example.joycenterserver.domain.attachment.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class InvalidAttachmentFileException extends GlobalException {
    public InvalidAttachmentFileException() {
        super(ErrorCode.INVALID_ATTACHMENT_FILE);
    }
}
