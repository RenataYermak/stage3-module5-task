package com.mjc.school.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.CommentRequestDto;
import com.mjc.school.service.dto.CommentResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentController extends BaseController<CommentRequestDto, CommentResponseDto, Long> {

    ResponseEntity<List<CommentResponseDto>> readByNewsId(Long id);
}