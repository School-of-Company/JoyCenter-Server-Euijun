package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.repository.MemberRepository;
import com.example.joycenterserver.domain.post.dto.request.PostCreateRequest;
import com.example.joycenterserver.domain.post.dto.response.PostCreateResponse;
import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostCreateService;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCreateServiceImpl implements PostCreateService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    @Override
    public PostCreateResponse create(PostCreateRequest request) {
        Long memberId = memberUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow();

        Post post = new Post(
                request.title(),
                request.content(),
                member
        );

        Post savedPost = postRepository.save(post);

        return new PostCreateResponse(savedPost.getPostId());
    }
}
