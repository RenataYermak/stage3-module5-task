package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Comment;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "news.id", source = "newsId")
    public abstract Comment mapCommentRequestDtoToComment(CommentRequestDto commentDtoRequest);

    @Mapping(target = "newsDto", ignore = true)
    public abstract CommentResponseDto mapCommentToCommentResponseDto(Comment comment);

    public abstract List<CommentResponseDto> listCommentsToCommentResponseDto(List<Comment> commentsList);
}
