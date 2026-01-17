package com.example.joycenterserver.domain.attachment.service.impl;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.attachment.exception.AttachmentForbiddenException;
import com.example.joycenterserver.domain.attachment.exception.NotFoundAttachmentException;
import com.example.joycenterserver.domain.attachment.repository.AttachmentRepository;
import com.example.joycenterserver.domain.attachment.service.AttachmentDeleteService;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentDeleteServiceImpl implements AttachmentDeleteService {

    private final AttachmentRepository attachmentRepository;
    private final MemberUtil memberUtil;

    @Override
    public void delete(Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(NotFoundAttachmentException::new);

        Long currentMemberId = memberUtil.getCurrentMemberId();
        Long uploaderId = attachment.getUploader().getMemberId();

        if (!uploaderId.equals(currentMemberId)) {
            throw new AttachmentForbiddenException();
        }

        attachmentRepository.delete(attachment);
    }
}
