package com.example.joycenterserver.domain.post.controller;

import com.example.joycenterserver.domain.post.dto.request.PostCreateRequest;
import com.example.joycenterserver.domain.post.dto.request.PostUpdateRequest;
import com.example.joycenterserver.domain.post.dto.response.PostCreateResponse;
import com.example.joycenterserver.domain.post.dto.response.PostDetailResponse;
import com.example.joycenterserver.domain.post.dto.response.PostListResponse;
import com.example.joycenterserver.domain.post.entity.PostSortType;
import com.example.joycenterserver.domain.post.service.PostCreateService;
import com.example.joycenterserver.domain.post.service.PostDeleteService;
import com.example.joycenterserver.domain.post.service.PostGetService;
import com.example.joycenterserver.domain.post.service.PostListService;
import com.example.joycenterserver.domain.post.service.PostUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostCreateService postCreateService;
    private final PostGetService postGetService;
    private final PostListService postListService;
    private final PostUpdateService postUpdateService;
    private final PostDeleteService postDeleteService;

    @PostMapping
    public ResponseEntity<PostCreateResponse> create(
            @RequestBody @Valid PostCreateRequest request
    ) {
        return ResponseEntity
                .status(201)
                .body(postCreateService.create(request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getById(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(
                postGetService.getById(postId)
        );
    }

    @GetMapping
    public ResponseEntity<PostListResponse> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "CREATED_AT_DESC") PostSortType sort
    ) {
        return ResponseEntity.ok(
                postListService.getList(page, size, sort)
        );
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> update(
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequest request
    ) {
        postUpdateService.update(postId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId
    ) {
        postDeleteService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
