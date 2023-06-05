package com.mjc.school.service.dto;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public final class AuthorResponseDto {

    private final Long id;
    @Size(min = 3, max = 15)
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime lastUpdateDate;

    public AuthorResponseDto(Long id,
                             String name,
                             LocalDateTime createDate,
                             LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }
}