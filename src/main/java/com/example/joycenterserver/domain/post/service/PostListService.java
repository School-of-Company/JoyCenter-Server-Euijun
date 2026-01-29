package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.response.PostListResponse;
import com.example.joycenterserver.domain.post.entity.PostSortType;

public interface PostListService {
    PostListResponse getList(int page, int size, PostSortType sortType);
}
