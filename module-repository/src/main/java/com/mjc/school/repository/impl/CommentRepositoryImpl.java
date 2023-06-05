package com.mjc.school.repository.impl;

import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.dto.CommentFilter;
import com.mjc.school.repository.model.Comment;
import com.mjc.school.repository.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CommentRepositoryImpl extends AbstractDBRepository<Comment, Long> implements CommentRepository {

    @Override
    public List<Comment> readByNewsId(Long newsId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);

        Root<Comment> root = criteriaQuery.from(Comment.class);

        Join<Comment, News> news = root.join("news");

        criteriaQuery.where(
                criteriaBuilder.equal(news.get("id"), newsId)
        );

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Comment> readAll(int page, int size, String sortBy) {
        return null;
    }
}


