package com.example.joycenterserver.domain.post.service;

import com.example.joycenterserver.domain.post.dto.response.PostListResponse;

public interface PostListService {

    PostListResponse getList(int page, int size, String sort);
}
