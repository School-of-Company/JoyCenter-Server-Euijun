package com.example.joycenterserver.domain.post.service.impl;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.attachment.exception.AttachmentForbiddenException;
import com.example.joycenterserver.domain.attachment.exception.NotFoundAttachmentException;
import com.example.joycenterserver.domain.attachment.repository.AttachmentRepository;
import com.example.joycenterserver.domain.post.dto.request.PostBlockRequest;
import com.example.joycenterserver.domain.post.dto.request.PostUpdateRequest;
import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.entity.PostBlock;
import com.example.joycenterserver.domain.post.exception.*;
import com.example.joycenterserver.domain.post.repository.PostBlockRepository;
import com.example.joycenterserver.domain.post.repository.PostRepository;
import com.example.joycenterserver.domain.post.service.PostUpdateService;
import com.example.joycenterserver.global.s3.S3Uploader;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostUpdateServiceImpl implements PostUpdateService {

    private final PostRepository postRepository;
    private final PostBlockRepository postBlockRepository;
    private final AttachmentRepository attachmentRepository;
    private final S3Uploader s3Uploader;
    private final MemberUtil memberUtil;

    @Override
    public void update(Long postId, PostUpdateRequest request) {
        if (request == null || (!request.hasTitle() && !request.hasBlocks())) {
            throw new InvalidPostRequestException();
        }

        Long memberId = memberUtil.getCurrentMemberId();

        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundPostException::new);

        if (!post.getMember().getMemberId().equals(memberId)) {
            throw new PostForbiddenException();
        }

        if (request.hasTitle()) {
            if (request.title() == null || request.title().isBlank()) {
                throw new InvalidPostTitleException();
            }
            post.update(request.title());
        }

        if (!request.hasBlocks()) {
            return;
        }

        List<PostBlockRequest> blocks = request.blocks();
        if (blocks == null || blocks.isEmpty()) {
            throw new InvalidPostRequestException();
        }

        List<PostBlock> existingBlocks =
                postBlockRepository.findAllByPostOrderByBlockOrderAsc(post);

        for (PostBlock block : existingBlocks) {
            if (block.getAttachment() != null) {
                s3Uploader.deleteByUrl(block.getAttachment().getUrl());
            }
        }

        postBlockRepository.deleteAll(existingBlocks);

        int order = 0;
        int totalTextLength = 0;

        for (PostBlockRequest block : blocks) {
            if (block == null || block.type() == null) {
                throw new InvalidPostRequestException();
            }

            if ("TEXT".equals(block.type())) {
                if (block.text() == null || block.text().isBlank()) {
                    throw new InvalidPostRequestException();
                }

                totalTextLength += block.text().length();
                postBlockRepository.save(
                        PostBlock.text(post, order++, block.text())
                );
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
                postBlockRepository.save(
                        PostBlock.attachment(post, order++, attachment)
                );
                continue;
            }

            throw new InvalidPostRequestException();
        }

        if (totalTextLength > 5000) {
            throw new InvalidPostContentException();
        }
    }
}
