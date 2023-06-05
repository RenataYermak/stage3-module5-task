package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.dto.AuthorFilter;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.annotation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Validate
    @Override
    public List<AuthorResponseDto> readAll(int page, int size, String sortBy) {
        var authorList = authorRepository.readAll(page, size, sortBy);
        return mapper.listAuthorsToAuthorResponseDto(authorList);
    }

    @Validate(value = "checkAuthorId")
    @Override
    public AuthorResponseDto readById(Long id) {
        if (authorRepository.existById(id)) {
            return mapper.mapAuthorToAuthorResponseDto(authorRepository.readById(id).get());
        } else {
            throw new NotFoundException(String.format("Author with ID %d not found.", id));
        }
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorResponseDto create(AuthorRequestDto authorRequestDto) {
        var author = mapper.mapAuthorRequestDtoToAuthor(authorRequestDto);
        var localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        author.setCreateDate(localDateTime);
        author.setLastUpdateDate(localDateTime);
        var createdAuthor = authorRepository.create(author);
        return mapper.mapAuthorToAuthorResponseDto(createdAuthor);
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorResponseDto update(AuthorRequestDto authorRequestDto) {
        if (authorRepository.existById(authorRequestDto.getId())) {
            var author = mapper.mapAuthorRequestDtoToAuthor(authorRequestDto);
            var localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            author.setName(authorRequestDto.getName());
            author.setLastUpdateDate(localDateTime);
            return mapper.mapAuthorToAuthorResponseDto(authorRepository.update(author));
        } else {
            throw new NotFoundException(String.format("Author with ID %d not found.", authorRequestDto.getId()));
        }
    }

    @Validate(value = "checkAuthorId")
    @Override
    public boolean deleteById(Long id) {
        if (authorRepository.existById(id)) {
            return authorRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Author with ID %d not found.", id));
        }
    }

    @Override
    public AuthorResponseDto readByNewsId(Long id) {
        Optional<Author> author = authorRepository.readByNewsId(id);
        return mapper.mapAuthorToAuthorResponseDto(author.get());

    }

}