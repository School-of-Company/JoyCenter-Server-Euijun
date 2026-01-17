package com.example.joycenterserver.domain.attachment.exception;

import com.example.joycenterserver.global.error.ErrorCode;
import com.example.joycenterserver.global.error.GlobalException;

public class AttachmentUploadFailedException extends GlobalException {
    public AttachmentUploadFailedException() {
        super(ErrorCode.ATTACHMENT_UPLOAD_FAILED);
    }
}
