package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.dto.NewsFilter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepositoryImpl extends AbstractDBRepository<News, Long> implements NewsRepository {

    @Override
    public List<News> readAll(int page, int size, String sortBy) {
        return null;
    }
}