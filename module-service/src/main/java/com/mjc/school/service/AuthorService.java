package com.mjc.school.service;

import com.mjc.school.repository.dto.AuthorFilter;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService extends BaseService<AuthorRequestDto, AuthorResponseDto, Long> {

    AuthorResponseDto readByNewsId(Long id);

}