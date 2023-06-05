package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface AuthorController extends BaseController<AuthorRequestDto, AuthorResponseDto, Long> {

    ResponseEntity<AuthorResponseDto> readByNewsId(Long id);
}