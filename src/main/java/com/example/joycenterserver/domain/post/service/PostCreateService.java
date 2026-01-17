package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.request.PostCreateRequest;
import com.example.joycenterserver.domain.post.dto.response.PostCreateResponse;

public interface PostCreateService {
    PostCreateResponse create(PostCreateRequest request);
}
