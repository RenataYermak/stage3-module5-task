package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.dto.AuthorFilter;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
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
public class AuthorRepositoryImpl  extends AbstractDBRepository<Author, Long> implements AuthorRepository {

    @Override
    public Optional<Author> readByNewsId(Long newsId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);

        Root<Author> root = query.from(Author.class);

        Join<Author, News> news = root.join("news");
        query.where(
                builder.equal(news.get("id"), newsId)
        );
        return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
    }

    @Override
    public List<Author> readAll(int page, int size, String sortBy) {
        return null;
    }
}
