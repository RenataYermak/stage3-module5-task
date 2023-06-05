package com.mjc.school.repository;

import com.mjc.school.repository.dto.AuthorFilter;
import com.mjc.school.repository.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorRepository extends BaseRepository<Author, Long> {

    Optional<Author> readByNewsId(Long newsId);

}
