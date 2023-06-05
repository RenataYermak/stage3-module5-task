package com.mjc.school.service.dto;

import javax.validation.constraints.Size;

public final class TagRequestDto {

    private final Long id;
    @Size(min = 3, max =15)
    private final String name;

    public TagRequestDto(Long id,
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
