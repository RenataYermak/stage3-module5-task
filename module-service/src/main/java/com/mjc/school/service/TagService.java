package com.mjc.school.service;

import com.mjc.school.repository.dto.TagFilter;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService extends BaseService<TagRequestDto, TagResponseDto, Long> {

    List<TagResponseDto> readByNewsId(Long id);

    Page<Tag> getComments(TagFilter params, Pageable pageable);
}