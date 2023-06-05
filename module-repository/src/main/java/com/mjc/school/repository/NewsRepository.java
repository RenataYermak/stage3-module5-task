package com.mjc.school.repository;

import com.mjc.school.repository.dto.NewsFilter;
import com.mjc.school.repository.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsRepository extends BaseRepository<News, Long> {
}
