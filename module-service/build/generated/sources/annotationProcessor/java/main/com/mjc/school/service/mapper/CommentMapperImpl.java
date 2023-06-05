package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Comment;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
import com.mjc.school.service.dto.NewsResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T18:53:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class CommentMapperImpl extends CommentMapper {

    @Override
    public Comment mapCommentRequestDtoToComment(CommentRequestDto commentDtoRequest) {
        if ( commentDtoRequest == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setNews( commentRequestDtoToNews( commentDtoRequest ) );
        comment.setId( commentDtoRequest.getId() );
        comment.setContent( commentDtoRequest.getContent() );

        return comment;
    }

    @Override
    public CommentResponseDto mapCommentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Long id = null;
        String content = null;
        LocalDateTime createDate = null;
        LocalDateTime lastUpdateDate = null;

        id = comment.getId();
        content = comment.getContent();
        createDate = comment.getCreateDate();
        lastUpdateDate = comment.getLastUpdateDate();

        NewsResponseDto newsDto = null;

        CommentResponseDto commentResponseDto = new CommentResponseDto( id, content, newsDto, createDate, lastUpdateDate );

        return commentResponseDto;
    }

    @Override
    public List<CommentResponseDto> listCommentsToCommentResponseDto(List<Comment> commentsList) {
        if ( commentsList == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( commentsList.size() );
        for ( Comment comment : commentsList ) {
            list.add( mapCommentToCommentResponseDto( comment ) );
        }

        return list;
    }

    protected News commentRequestDtoToNews(CommentRequestDto commentRequestDto) {
        if ( commentRequestDto == null ) {
            return null;
        }

        News news = new News();

        news.setId( commentRequestDto.getNewsId() );

        return news;
    }
}
