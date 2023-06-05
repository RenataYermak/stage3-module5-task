package com.mjc.school.repository;

import com.mjc.school.repository.dto.CommentFilter;
import com.mjc.school.repository.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> readByNewsId(Long id);
}
