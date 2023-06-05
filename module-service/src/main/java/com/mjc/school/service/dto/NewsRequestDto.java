package com.mjc.school.service.dto;

import javax.validation.constraints.Size;

public final class NewsRequestDto {

    private final Long id;
    @Size(min = 5, max = 30)
    private final String title;
    @Size(min = 5, max = 255)
    private final String content;
    private final Long authorId;
    private final Long commentId;
    private final Long tagId;

    public NewsRequestDto(Long id, String title, String content, Long authorId, Long commentId, Long tagId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.commentId = commentId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getTagId() {
        return tagId;
    }
}
