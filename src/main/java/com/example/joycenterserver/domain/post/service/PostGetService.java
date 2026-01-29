package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.response.PostDetailResponse;
import com.example.joycenterserver.domain.post.dto.response.PostListResponse;
import com.example.joycenterserver.domain.post.entity.PostSortType;

public interface PostGetService {

    PostDetailResponse getById(Long postId);
    PostListResponse getList(PostSortType sortType);
}
