package com.example.joycenterserver.domain.attachment.service;

import com.example.joycenterserver.domain.attachment.dto.AttachmentUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentUploadService {
    AttachmentUploadResponse upload(MultipartFile file, String type);
}
