package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.CommentRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.dto.NewsFilter;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.annotation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final AuthorRepository authorRepository;
    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;
    private final NewsMapper mapper;

    @Autowired
    public NewsServiceImpl(AuthorRepository authorRepository, NewsRepository newsRepository, CommentRepository commentRepository, NewsMapper mapper) {
        this.authorRepository = authorRepository;
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<NewsResponseDto> readAll(int page, int size, String sortBy) {
        var newsList = newsRepository.readAll(page, size, sortBy);
        return mapper.mapNewsToNewsResponseDtoList(newsList);
    }

    @Validate(value = "checkNewsId")
    @Override
    public NewsResponseDto readById(Long id) {
        if (newsRepository.existById(id)) {
            var optionalNews = newsRepository.readById(id);
            return mapper.mapNewsToNewsResponseDto(optionalNews.get());
        } else {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
    }

    @Validate(value = "checkNews")
    @Override
    public NewsResponseDto create(NewsRequestDto newsRequestDto) {
        if (authorRepository.existById(newsRequestDto.getAuthorId())) {
            var news = mapper.mapNewsRequestDtoToNews(newsRequestDto);
            var localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            news.setCreateDate(localDateTime);
            news.setLastUpdateDate(localDateTime);
            var savedNews = newsRepository.create(news);
            return mapper.mapNewsToNewsResponseDto(savedNews);
        } else {
            throw new NotFoundException(
                    String.format("Author with ID %d not found.", newsRequestDto.getAuthorId()));
        }
    }

    @Validate(value = "checkNews")
    @Override
    public NewsResponseDto update(NewsRequestDto newsRequestDto) {
        if (authorRepository.existById(newsRequestDto.getAuthorId())) {
            if (newsRepository.existById(newsRequestDto.getId())) {
                var news = mapper.mapNewsRequestDtoToNews(newsRequestDto);
                var updatedDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
                news.setLastUpdateDate(updatedDate);
                var savedNews = newsRepository.update(news);
                return mapper.mapNewsToNewsResponseDto(savedNews);
            } else {
                throw new NotFoundException(String.format("News with ID %d not found.", newsRequestDto.getId()));
            }
        } else {
            throw new NotFoundException(
                    String.format("Author with ID %d not found.", newsRequestDto.getAuthorId()));
        }
    }

    @Validate(value = "checkNewsId")
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)) {
            return newsRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("News with ID %d not found.", id));
        }
    }

    @Override
    public Page<News> getComments(NewsFilter params, Pageable pageable) {
        return null;
    }
}
