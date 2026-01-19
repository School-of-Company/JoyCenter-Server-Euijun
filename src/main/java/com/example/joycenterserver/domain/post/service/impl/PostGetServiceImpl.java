package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.post.dto.response.PostBlockResponse;
import com.example.joycenterserver.domain.post.dto.response.PostDetailResponse;
import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.exception.NotFoundPostException;
import com.example.joycenterserver.domain.post.repository.PostBlockRepository;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostGetServiceImpl implements PostGetService {

    private final PostRepository postRepository;
    private final PostBlockRepository postBlockRepository;

    @Override
    public PostDetailResponse getById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        List<PostBlockResponse> blocks = postBlockRepository.findAllByPostOrderByBlockOrderAsc(post)
                .stream()
                .map(PostBlockResponse::from)
                .toList();

        return PostDetailResponse.from(post, blocks);
    }
}
