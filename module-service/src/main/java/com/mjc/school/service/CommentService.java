package com.mjc.school.service;

import com.mjc.school.repository.dto.CommentFilter;
import com.mjc.school.repository.dto.NewsFilter;
import com.mjc.school.repository.model.Comment;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService extends BaseService<CommentRequestDto, CommentResponseDto, Long> {

    List<CommentResponseDto> readByNewsId(Long id);

    Page<Comment> getComments(CommentFilter params, Pageable pageable);
}