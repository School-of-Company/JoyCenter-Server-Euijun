package com.example.joycenterserver.domain.post.repository;

import com.example.joycenterserver.domain.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"member"})
    Optional<Post> findWithMemberByPostId(Long postId);

    @EntityGraph(attributePaths = {"member"})
    org.springframework.data.domain.Page<Post> findAll(org.springframework.data.domain.Pageable pageable);
}
