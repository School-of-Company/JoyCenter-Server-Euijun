package com.example.joycenterserver.domain.post.dto.response;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.post.entity.PostBlock;

public record PostBlockResponse(
        String type,
        String text,
        AttachmentInfo attachment
) {

    public static PostBlockResponse from(PostBlock block) {
        if (block.getType().name().equals("TEXT")) {
            return new PostBlockResponse("TEXT", block.getText(), null);
        }

        Attachment attachment = block.getAttachment();
        return new PostBlockResponse(
                "ATTACHMENT",
                null,
                attachment == null ? null : AttachmentInfo.from(attachment)
        );
    }

    public record AttachmentInfo(
            Long attachmentId,
            String attachmentType,
            String url,
            String originalFilename,
            Long size
    ) {
        public static AttachmentInfo from(Attachment attachment) {
            return new AttachmentInfo(
                    attachment.getAttachmentId(),
                    attachment.getType().name(),
                    attachment.getUrl(),
                    attachment.getOriginalFilename(),
                    attachment.getSize()
            );
        }
    }
}
