package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Author;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    public abstract Author mapAuthorRequestDtoToAuthor(AuthorRequestDto authorDtoRequest);

    public abstract  AuthorResponseDto mapAuthorToAuthorResponseDto(Author author);

    public abstract List<AuthorResponseDto> listAuthorsToAuthorResponseDto(List<Author> authorsList);
}
