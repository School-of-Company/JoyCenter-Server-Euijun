package com.example.joycenterserver.domain.attachment.controller;

import com.example.joycenterserver.domain.attachment.dto.AttachmentUploadResponse;
import com.example.joycenterserver.domain.attachment.service.AttachmentDeleteService;
import com.example.joycenterserver.domain.attachment.service.AttachmentUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {

    private final AttachmentUploadService attachmentUploadService;
    private final AttachmentDeleteService attachmentDeleteService;

    @PostMapping
    public ResponseEntity<AttachmentUploadResponse> upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        return ResponseEntity.ok(attachmentUploadService.upload(file, type));
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<Void> delete(@PathVariable Long attachmentId) {
        attachmentDeleteService.delete(attachmentId);
        return ResponseEntity.noContent().build();
    }
}
