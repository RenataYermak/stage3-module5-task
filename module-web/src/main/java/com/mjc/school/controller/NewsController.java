package com.mjc.school.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsController extends BaseController<NewsRequestDto, NewsResponseDto, Long> {
}
