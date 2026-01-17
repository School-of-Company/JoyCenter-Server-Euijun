package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.response.PostDetailResponse;

public interface PostGetService {

    PostDetailResponse getById(Long postId);
}
