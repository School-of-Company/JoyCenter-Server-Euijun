package com.example.joycenterserver.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PostCreateRequest(

        @NotBlank
        @Size(max = 100)
        String title,

        List<PostBlockRequest> blocks
) {
}
