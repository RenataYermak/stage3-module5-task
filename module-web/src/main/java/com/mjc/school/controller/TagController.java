package com.mjc.school.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagController extends BaseController<TagRequestDto, TagResponseDto, Long> {

    ResponseEntity<List<TagResponseDto>> readByNewsId(Long id);
}
