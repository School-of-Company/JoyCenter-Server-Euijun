package com.example.joycenterserver.domain.post.dto.response;

import com.example.joycenterserver.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse(
        Long postId,
        String title,
        LocalDateTime createdAt,
        Long memberId,
        String memberEmail,
        List<PostBlockResponse> blocks
) {

    public static PostDetailResponse from(Post post, List<PostBlockResponse> blocks) {
        return new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getMember().getMemberId(),
                post.getMember().getEmail(),
                blocks
        );
    }
}
