package com.example.joycenterserver.domain.attachment.dto;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.attachment.entity.AttachmentType;

public record AttachmentUploadResponse(
        Long attachmentId,
        AttachmentType type,
        String url
) {
    public static AttachmentUploadResponse from(Attachment attachment) {
        return new AttachmentUploadResponse(
                attachment.getAttachmentId(),
                attachment.getType(),
                attachment.getUrl()
        );
    }
}
