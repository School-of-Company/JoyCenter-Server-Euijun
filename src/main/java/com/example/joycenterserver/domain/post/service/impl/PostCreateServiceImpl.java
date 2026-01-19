package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.attachment.exception.AttachmentForbiddenException;
import com.example.joycenterserver.domain.attachment.exception.NotFoundAttachmentException;
import com.example.joycenterserver.domain.attachment.repository.AttachmentRepository;
import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.member.exception.NotFoundMemberException;
import com.example.joycenterserver.domain.member.repository.MemberRepository;
import com.example.joycenterserver.domain.post.dto.request.PostBlockRequest;
import com.example.joycenterserver.domain.post.dto.request.PostCreateRequest;
import com.example.joycenterserver.domain.post.dto.response.PostCreateResponse;
import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.entity.PostBlock;
import com.example.joycenterserver.domain.post.exception.InvalidPostContentException;
import com.example.joycenterserver.domain.post.exception.InvalidPostRequestException;
import com.example.joycenterserver.domain.post.repository.PostBlockRepository;
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
    private final PostBlockRepository postBlockRepository;
    private final AttachmentRepository attachmentRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    @Override
    public PostCreateResponse create(PostCreateRequest request) {
        Long memberId = memberUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        if (request.blocks() == null || request.blocks().isEmpty()) {
            throw new InvalidPostRequestException();
        }

        Post post = new Post(request.title(), member);
        postRepository.save(post);

        int order = 0;
        int totalTextLength = 0;

        for (PostBlockRequest block : request.blocks()) {
            if (block == null || block.type() == null) {
                throw new InvalidPostRequestException();
            }

            if ("TEXT".equals(block.type())) {
                if (block.text() == null || block.text().isBlank()) {
                    throw new InvalidPostRequestException();
                }

                totalTextLength += block.text().length();
                postBlockRepository.save(PostBlock.text(post, order++, block.text()));
                continue;
            }

            if ("ATTACHMENT".equals(block.type())) {
                if (block.attachmentId() == null) {
                    throw new InvalidPostRequestException();
                }

                Attachment attachment = attachmentRepository.findById(block.attachmentId())
                        .orElseThrow(NotFoundAttachmentException::new);

                if (!attachment.getUploader().getMemberId().equals(memberId)) {
                    throw new AttachmentForbiddenException();
                }

                attachment.assignPost(post);
                postBlockRepository.save(PostBlock.attachment(post, order++, attachment));
                continue;
            }

            throw new InvalidPostRequestException();
        }

        if (totalTextLength > 5000) {
            throw new InvalidPostContentException();
        }

        return new PostCreateResponse(post.getPostId());
    }
}
