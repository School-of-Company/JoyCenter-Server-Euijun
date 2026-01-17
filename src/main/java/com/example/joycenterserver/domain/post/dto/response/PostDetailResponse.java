package com.example.joycenterserver.domain.post.dto.response;

import com.example.joycenterserver.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostDetailResponse(
        Long postId,
        String title,
        String content,
        LocalDateTime createdAt,
        Long memberId,
        String memberEmail
) {

    public static PostDetailResponse from(Post post) {
        return new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getMember().getMemberId(),
                post.getMember().getEmail()
        );
    }
}
