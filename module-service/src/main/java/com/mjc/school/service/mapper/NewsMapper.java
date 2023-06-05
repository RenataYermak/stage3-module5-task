package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NewsMapper {

    @Autowired
    protected BaseService<AuthorRequestDto, AuthorResponseDto, Long> authorService;


    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "author.id", source = "authorId")
    public abstract News mapNewsRequestDtoToNews(NewsRequestDto dto);

    @Mapping(target = "tagId", ignore = true)
    @Mapping(target = "commentDto", ignore = true)
    @Mapping(target = "authorDto", expression =
            "java(news.getAuthor().getId() != null ? authorService.readById(news.getAuthor().getId()) : null)")
    public abstract NewsResponseDto mapNewsToNewsResponseDto(News news);

    public abstract List<NewsResponseDto> mapNewsToNewsResponseDtoList(List<News> newsCollection);
}

