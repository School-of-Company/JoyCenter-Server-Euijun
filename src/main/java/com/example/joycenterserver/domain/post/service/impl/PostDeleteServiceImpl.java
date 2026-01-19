package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.entity.PostBlock;
import com.example.joycenterserver.domain.post.exception.NotFoundPostException;
import com.example.joycenterserver.domain.post.exception.PostForbiddenException;
import com.example.joycenterserver.domain.post.repository.PostBlockRepository;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostDeleteService;
import com.example.joycenterserver.global.s3.S3Uploader;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostDeleteServiceImpl implements PostDeleteService {

    private final PostRepository postRepository;
    private final PostBlockRepository postBlockRepository;
    private final S3Uploader s3Uploader;
    private final MemberUtil memberUtil;

    @Override
    public void delete(Long postId) {
        Long memberId = memberUtil.getCurrentMemberId();

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if (!post.getMember().getMemberId().equals(memberId)) {
            throw new PostForbiddenException();
        }

        List<PostBlock> blocks = postBlockRepository.findAllByPostOrderByBlockOrderAsc(post);

        for (PostBlock block : blocks) {
            if (block.getAttachment() != null) {
                s3Uploader.deleteByUrl(block.getAttachment().getUrl());
            }
        }

        postBlockRepository.deleteAll(blocks);
        postRepository.delete(post);
    }
}
