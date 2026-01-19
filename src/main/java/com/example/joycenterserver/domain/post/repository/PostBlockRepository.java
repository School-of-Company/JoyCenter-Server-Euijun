package com.example.joycenterserver.domain.post.repository;

import com.example.joycenterserver.domain.post.entity.Post;
import com.example.joycenterserver.domain.post.entity.PostBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostBlockRepository extends JpaRepository<PostBlock, Long> {

    List<PostBlock> findAllByPostOrderByBlockOrderAsc(Post post);

    void deleteAllByPost(Post post);
}
