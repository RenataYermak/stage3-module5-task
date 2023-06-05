package com.mjc.school.repository.dto;

public record CommentFilter(
        String content,
        Long newsId) {
}
