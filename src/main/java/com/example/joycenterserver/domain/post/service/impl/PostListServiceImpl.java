package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.post.dto.response.PostListResponse;
import com.example.joycenterserver.domain.post.entity.PostSortType;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostListServiceImpl implements PostListService {

    private final PostRepository postRepository;

    @Override
    public PostListResponse getList(int page, int size, PostSortType sortType) {
        Pageable pageable = PageRequest.of(page, size, sortType.toSort());
        return PostListResponse.from(postRepository.findAll(pageable));
    }
}
