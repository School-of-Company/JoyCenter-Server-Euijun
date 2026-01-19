package com.example.joycenterserver.domain.post.dto.request;

import jakarta.validation.constraints.Size;

import java.util.List;

public record PostUpdateRequest(

        @Size(max = 100)
        String title,

        List<PostBlockRequest> blocks
) {

        public boolean hasTitle() {
                return title != null;
        }

        public boolean hasBlocks() {
                return blocks != null;
        }
}
