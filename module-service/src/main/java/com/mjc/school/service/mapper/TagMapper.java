package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TagMapper {

    @Mapping(target = "news", ignore = true)
    public abstract Tag mapTagRequestDtoToTag(TagRequestDto tagDtoRequest);

    public abstract TagResponseDto mapTagToTagResponseDto(Tag tag);

    public abstract List<TagResponseDto> listTagsToTagResponseDto(List<Tag> tagModel);
}

