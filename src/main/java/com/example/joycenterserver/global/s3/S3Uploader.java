package com.example.joycenterserver.global.s3;

import com.example.joycenterserver.domain.attachment.exception.AttachmentUploadFailedException;
import com.example.joycenterserver.domain.attachment.exception.InvalidAttachmentFileException;
import com.example.joycenterserver.domain.attachment.exception.InvalidAttachmentFileTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    public String upload(MultipartFile file, String type) {
        validate(file, type);

        String key = "attachments/" + type.toLowerCase() + "/" +
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(awsProperties.getBucket())
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            throw new AttachmentUploadFailedException();
        }

        return "https://" + awsProperties.getBucket()
                + ".s3." + awsProperties.getRegion()
                + ".amazonaws.com/" + key;
    }

    private void validate(MultipartFile file, String type) {
        if (file == null || file.isEmpty()) {
            throw new InvalidAttachmentFileException();
        }

        String name = file.getOriginalFilename();
        String ext = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
        String mime = file.getContentType();

        if (!AllowedFilePolicy.isAllowed(ext, mime, type)) {
            throw new InvalidAttachmentFileTypeException();
        }
    }
}
