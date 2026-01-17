package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.exception.PostForbiddenException;
import com.example.joycenterserver.domain.post.exception.NotFoundPostException;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostDeleteService;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostDeleteServiceImpl implements PostDeleteService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    @Override
    public void delete(Long postId) {
        Long memberId = memberUtil.getCurrentMemberId();

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if (!post.getMember().getMemberId().equals(memberId)) {
            throw new PostForbiddenException();
        }

        postRepository.delete(post);
    }
}
