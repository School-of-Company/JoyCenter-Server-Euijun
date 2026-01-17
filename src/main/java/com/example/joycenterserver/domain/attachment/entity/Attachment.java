package com.example.joycenterserver.domain.attachment.entity;

import com.example.joycenterserver.domain.member.entity.Member;
import com.example.joycenterserver.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attachment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttachmentType type;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(nullable = false, length = 255)
    private String originalFilename;

    @Column(nullable = false, length = 100)
    private String contentType;

    @Column(nullable = false)
    private Long size;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member uploader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Attachment(AttachmentType type, String url, String originalFilename, String contentType, Long size, Member uploader) {
        this.type = type;
        this.url = url;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.uploader = uploader;
    }

    public void assignPost(Post post) {
        this.post = post;
    }
}
