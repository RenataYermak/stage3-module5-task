package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsFilter;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService extends BaseService<NewsRequestDto, NewsResponseDto, Long> {

    Page<News> getComments(NewsFilter params, Pageable pageable);
}