package com.mjc.school.repository;

import com.mjc.school.repository.dto.TagFilter;
import com.mjc.school.repository.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag, Long> {

    List<Tag> readByNewsId(Long newsId);
}
