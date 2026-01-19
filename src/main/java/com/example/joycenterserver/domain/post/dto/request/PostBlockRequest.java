package com.example.joycenterserver.domain.post.dto.request;

public record PostBlockRequest(
        String type,
        String text,
        Long attachmentId
) {
}
