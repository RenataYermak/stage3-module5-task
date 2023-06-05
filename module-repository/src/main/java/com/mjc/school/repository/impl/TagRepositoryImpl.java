package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.dto.TagFilter;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl extends AbstractDBRepository<Tag, Long> implements TagRepository {

    @Override
    public List<Tag> readByNewsId(Long newsId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);

        Root<Tag> root = criteriaQuery.from(Tag.class);

        Join<Tag, News> news = root.join("news");

        criteriaQuery.where(
                criteriaBuilder.equal(news.get("id"), newsId)
        );

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public List<Tag> readAll(int page, int size, String sortBy) {
        return null;
    }
}