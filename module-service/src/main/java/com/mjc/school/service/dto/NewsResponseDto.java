package com.mjc.school.service.dto;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public final class NewsResponseDto {

    private final Long id;
    @Size(min = 5, max = 30)
    private final String title;
    @Size(min = 5, max = 255)
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdateDate;
    private final AuthorResponseDto authorDto;
    private final CommentResponseDto commentDto;
    private final Long tagId;

    public NewsResponseDto(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, AuthorResponseDto authorDto, CommentResponseDto commentDto, Long tagId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorDto = authorDto;
        this.commentDto = commentDto;
        this.tagId = tagId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public AuthorResponseDto getAuthorDto() {
        return authorDto;
    }

    public CommentResponseDto getCommentDto() {
        return commentDto;
    }

    public Long getTagId() {
        return tagId;
    }
}