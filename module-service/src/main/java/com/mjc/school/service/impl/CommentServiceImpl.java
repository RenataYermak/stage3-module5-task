package com.mjc.school.service.impl;

import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.dto.CommentFilter;
import com.mjc.school.repository.model.Comment;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.CommentMapper;
import com.mjc.school.service.validation.annotation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, NewsRepository newsRepository, CommentMapper mapper) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CommentResponseDto> readAll(int page, int size, String sortBy) {
        var commentList = commentRepository.readAll(page, size, sortBy);
        return mapper.listCommentsToCommentResponseDto(commentList);
    }

    @Validate(value = "checkCommentId")
    @Override
    public CommentResponseDto readById(Long id) {
        if (commentRepository.existById(id)) {
            var optionalComment = commentRepository.readById(id);
            return mapper.mapCommentToCommentResponseDto(optionalComment.get());
        } else {
            throw new NotFoundException(String.format("Comment with ID %d not found.", id));
        }
    }

    @Validate(value = "checkComment")
    @Override
    public CommentResponseDto create(CommentRequestDto commentRequestDto) {
        if (newsRepository.existById(commentRequestDto.getNewsId())) {
            var comment = mapper.mapCommentRequestDtoToComment(commentRequestDto);
            var localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            comment.setCreateDate(localDateTime);
            comment.setLastUpdateDate(localDateTime);
            var savedComment = commentRepository.create(comment);
            return mapper.mapCommentToCommentResponseDto(savedComment);
        } else {
            throw new NotFoundException(
                    String.format("Author with ID %d not found.", commentRequestDto.getNewsId()));
        }
    }

    @Validate(value = "checkComment")
    @Override
    public CommentResponseDto update(CommentRequestDto commentRequestDto) {
        if (newsRepository.existById(commentRequestDto.getNewsId())) {
            if (commentRepository.existById(commentRequestDto.getId())) {
                var comment = mapper.mapCommentRequestDtoToComment(commentRequestDto);
                var updatedDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
                comment.setLastUpdateDate(updatedDate);
                var savedComment = commentRepository.update(comment);
                return mapper.mapCommentToCommentResponseDto(savedComment);
            } else {
                throw new NotFoundException(String.format("Comment with ID %d not found.", commentRequestDto.getId()));
            }
        } else {
            throw new NotFoundException(
                    String.format("Author with ID %d not found.", commentRequestDto.getNewsId()));
        }
    }

    @Validate(value = "checkCommentId")
    public boolean deleteById(Long id) {
        if (commentRepository.existById(id)) {
            return commentRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Comment with ID %d not found.", id));
        }
    }

    @Override
    public List<CommentResponseDto> readByNewsId(Long id) {
        List<Comment> commentsByNewsId = commentRepository.readByNewsId(id);
        return mapper.listCommentsToCommentResponseDto(commentsByNewsId);
    }

    @Override
    public Page<Comment> getComments(CommentFilter params, Pageable pageable) {
        return null;
    }
}
