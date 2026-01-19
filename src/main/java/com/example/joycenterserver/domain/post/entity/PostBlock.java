package com.example.joycenterserver.domain.post.entity;

import com.example.joycenterserver.domain.attachment.entity.Attachment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_block")
public class PostBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postBlockId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostBlockType type;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @Column(nullable = false)
    private Integer blockOrder;

    private PostBlock(Post post, PostBlockType type, Integer blockOrder, String text, Attachment attachment) {
        this.post = post;
        this.type = type;
        this.blockOrder = blockOrder;
        this.text = text;
        this.attachment = attachment;
    }

    public static PostBlock text(Post post, Integer blockOrder, String text) {
        return new PostBlock(post, PostBlockType.TEXT, blockOrder, text, null);
    }

    public static PostBlock attachment(Post post, Integer blockOrder, Attachment attachment) {
        return new PostBlock(post, PostBlockType.ATTACHMENT, blockOrder, null, attachment);
    }
}
