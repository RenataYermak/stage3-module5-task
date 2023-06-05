package com.mjc.school.service.dto;

import javax.validation.constraints.Size;

public final class CommentRequestDto {

    private final Long id;
    @Size(min = 5, max = 255)
    private final String content;
    private final Long newsId;

    public CommentRequestDto(Long id, String content, Long newsId) {
        this.id = id;
        this.content = content;
        this.newsId = newsId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getNewsId() {
        return newsId;
    }
}
