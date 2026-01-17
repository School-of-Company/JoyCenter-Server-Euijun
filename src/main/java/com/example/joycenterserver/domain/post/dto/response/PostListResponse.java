package com.example.joycenterserver.domain.post.dto.response;

import com.example.joycenterserver.domain.post.entity.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public record PostListResponse(
        List<PostSummary> content,
        PageInfo page
) {

    public static PostListResponse from(Page<Post> postPage) {
        return new PostListResponse(
                postPage.getContent().stream()
                        .map(PostSummary::from)
                        .toList(),
                PageInfo.from(postPage)
        );
    }

    public record PostSummary(
            Long postId,
            String title,
            LocalDateTime createdAt,
            Long memberId,
            String memberEmail
    ) {
        public static PostSummary from(Post post) {
            return new PostSummary(
                    post.getPostId(),
                    post.getTitle(),
                    post.getCreatedAt(),
                    post.getMember().getMemberId(),
                    post.getMember().getEmail()
            );
        }
    }

    public record PageInfo(
            int number,
            int size,
            long totalElements,
            int totalPages,
            boolean hasNext,
            boolean hasPrevious
    ) {
        public static PageInfo from(Page<?> page) {
            return new PageInfo(
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.hasNext(),
                    page.hasPrevious()
            );
        }
    }
}
