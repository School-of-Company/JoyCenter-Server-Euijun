package com.example.joycenterserver.domain.attachment.service.impl;

import com.example.joycenterserver.domain.attachment.dto.AttachmentUploadResponse;
import com.example.joycenterserver.domain.attachment.entity.Attachment;
import com.example.joycenterserver.domain.attachment.entity.AttachmentType;
import com.example.joycenterserver.domain.attachment.repository.AttachmentRepository;
import com.example.joycenterserver.domain.attachment.service.AttachmentUploadService;
import com.example.joycenterserver.global.s3.S3Uploader;
import com.example.joycenterserver.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentUploadServiceImpl implements AttachmentUploadService {

    private final S3Uploader s3Uploader;
    private final AttachmentRepository attachmentRepository;
    private final MemberUtil memberUtil;

    @Override
    public AttachmentUploadResponse upload(MultipartFile file, String type) {
        AttachmentType attachmentType = AttachmentType.valueOf(type);
        String url = s3Uploader.upload(file, type);

        Attachment attachment = new Attachment(
                attachmentType,
                url,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                memberUtil.getCurrentMember()
        );

        return AttachmentUploadResponse.from(attachmentRepository.save(attachment));
    }
}
