package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.request.PostUpdateRequest;

public interface PostUpdateService {

    void update(Long postId, PostUpdateRequest request);
}
