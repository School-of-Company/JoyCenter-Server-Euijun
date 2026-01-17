package com.example.joycenterserver.domain.attachment.repository;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
