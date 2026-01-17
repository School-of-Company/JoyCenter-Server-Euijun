package com.example.joycenterserver.domain.post.dto.request;

import jakarta.validation.constraints.Size;

public record PostUpdateRequest(

        @Size(max = 100)
        String title,

        @Size(max = 5000)
        String content
) {
        public boolean hasTitle() {
                return title != null;
        }

        public boolean hasContent() {
                return content != null;
        }
}
