package com.example.joycenterserver.domain.post.entity;

import org.springframework.data.domain.Sort;

public enum PostSortType {

    CREATED_AT_DESC,
    CREATED_AT_ASC;

    public Sort toSort() {
        return switch (this) {
            case CREATED_AT_DESC -> Sort.by(Sort.Direction.DESC, "createdAt");
            case CREATED_AT_ASC -> Sort.by(Sort.Direction.ASC, "createdAt");
        };
    }
}
