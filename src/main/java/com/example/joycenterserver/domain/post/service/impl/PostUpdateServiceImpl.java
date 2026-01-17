package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.post.dto.request.PostUpdateRequest;
import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.exception.NotFoundPostException;
import com.example.joycenterserver.domain.post.exception.PostForbiddenException;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostUpdateService;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostUpdateServiceImpl implements PostUpdateService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Override
    public void update(Long postId, PostUpdateRequest request) {
        Long memberId = memberUtil.getCurrentMemberId();

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if (!post.getMember().getMemberId().equals(memberId)) {
            throw new PostForbiddenException();
        }

        post.update(request.title(), request.content());
    }
}
