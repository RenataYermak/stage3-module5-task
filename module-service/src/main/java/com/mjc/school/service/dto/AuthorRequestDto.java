package com.mjc.school.service.dto;

import javax.validation.constraints.Size;

public final class AuthorRequestDto {

    private final Long id;
    @Size(min = 3, max = 15)
    private final String name;

    public AuthorRequestDto(Long id,
                            String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
