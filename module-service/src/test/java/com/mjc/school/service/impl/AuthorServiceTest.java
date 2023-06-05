//package com.mjc.school.service.impl;
//
//import com.mjc.school.repository.impl.AuthorRepositoryImpl;
//import com.mjc.school.repository.model.Author;
//import com.mjc.school.service.dto.AuthorRequestDto;
//import com.mjc.school.service.dto.AuthorResponseDto;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.mapper.AuthorMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.mjc.school.service.constant.Constant.AUTHOR_ID_ONE;
//import static com.mjc.school.service.constant.Constant.AUTHOR_ID_TWO;
//import static com.mjc.school.service.constant.Constant.AUTHOR_NAME;
//import static com.mjc.school.service.constant.Constant.AUTHOR_UPDATED_NAME;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class AuthorServiceTest {
//
//    @Mock
//    private AuthorMapper mapper;
//
//    @Mock
//    private AuthorRepositoryImpl authorRepository;
//
//    @InjectMocks
//    private AuthorServiceImpl authorService;
//
//    LocalDateTime now = LocalDateTime.now();
//
//    @Test
//    void readAll() {
//        List<Author> authors = new ArrayList<>();
//        authors.add(new Author(AUTHOR_ID_ONE, AUTHOR_NAME));
//        authors.add(new Author(AUTHOR_ID_TWO, AUTHOR_UPDATED_NAME));
//
//        List<AuthorResponseDto> authorResponseDto = new ArrayList<>();
//        authorResponseDto.add(new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now));
//        authorResponseDto.add(new AuthorResponseDto(AUTHOR_ID_TWO, AUTHOR_UPDATED_NAME, now, now));
//
//        when(authorRepository.readAll()).thenReturn(authors);
//        when(mapper.listAuthorsToAuthorResponseDto(authors)).thenReturn(authorResponseDto);
//
//        var result = authorService.readAll();
//
//        assertEquals(authorResponseDto, result);
//    }
//
//    @Test
//    void readById() {
//        var author = new Author();
//        author.setId(AUTHOR_ID_ONE);
//        author.setName(AUTHOR_NAME);
//        author.setCreateDate(now);
//        author.setLastUpdateDate(now);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now);
//
//        when(authorRepository.existById(anyLong())).thenReturn(true);
//        when(authorRepository.readById(anyLong())).thenReturn(Optional.of(author));
//        when(mapper.mapAuthorToAuthorResponseDto(author)).thenReturn(authorResponseDto);
//
//        AuthorResponseDto result = authorService.readById(AUTHOR_ID_ONE);
//
//        assertEquals(authorResponseDto, result);
//    }
//
//
//    @Test
//    void create() throws NotFoundException {
//        var authorDto = new AuthorRequestDto(AUTHOR_ID_ONE, AUTHOR_NAME);
//        var author = new Author(AUTHOR_ID_ONE, AUTHOR_NAME);
//        var savedAuthor = new Author(AUTHOR_ID_ONE, AUTHOR_NAME);
//        savedAuthor.setCreateDate(now);
//        savedAuthor.setLastUpdateDate(now);
//
//        when(mapper.mapAuthorRequestDtoToAuthor(authorDto)).thenReturn(author);
//        when(authorRepository.create(any(Author.class))).thenReturn(savedAuthor);
//        when(mapper.mapAuthorToAuthorResponseDto(savedAuthor)).thenReturn(new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now));
//
//        var result = authorService.create(authorDto);
//
//        assertEquals(new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now), result);
//        verify(mapper, times(1)).mapAuthorRequestDtoToAuthor(authorDto);
//        verify(authorRepository, times(1)).create(author);
//        verify(mapper, times(1)).mapAuthorToAuthorResponseDto(savedAuthor);
//
//    }
//
//    @Test
//    void update() throws NotFoundException {
//        var author = new Author();
//        author.setId(AUTHOR_ID_ONE);
//        author.setName(AUTHOR_NAME);
//        author.setCreateDate(now);
//        author.setLastUpdateDate(now);
//
//        var updetedAuthor = new Author();
//        updetedAuthor.setId(AUTHOR_ID_ONE);
//        updetedAuthor.setName(AUTHOR_NAME);
//        updetedAuthor.setCreateDate(now);
//        updetedAuthor.setLastUpdateDate(now);
//
//        var authorRequestDto = new AuthorRequestDto(AUTHOR_ID_ONE, AUTHOR_NAME);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_UPDATED_NAME, now, now);
//
//        when(authorRepository.existById(AUTHOR_ID_ONE)).thenReturn(true);
//        when(mapper.mapAuthorRequestDtoToAuthor(authorRequestDto)).thenReturn(author);
//        when(authorRepository.update(any(Author.class))).thenReturn(updetedAuthor);
//        when(mapper.mapAuthorToAuthorResponseDto(any(Author.class))).thenReturn(authorResponseDto);
//
//        var result = authorService.update(authorRequestDto);
//
//        assertEquals(authorResponseDto, result);
//    }
//
//    @Test
//    void deleteById() {
//        when(authorRepository.existById(AUTHOR_ID_ONE)).thenReturn(true);
//        when(authorRepository.deleteById(AUTHOR_ID_ONE)).thenReturn(true);
//
//        var deleted = authorService.deleteById(AUTHOR_ID_ONE);
//
//        assertTrue(deleted);
//        verify(authorRepository, times(1)).existById(AUTHOR_ID_ONE);
//        verify(authorRepository, times(1)).deleteById(AUTHOR_ID_ONE);
//    }
//
//    @Test
//    void deleteByIdNonExisting() {
//        when(authorRepository.existById(AUTHOR_ID_ONE)).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> authorService.deleteById(AUTHOR_ID_ONE));
//        verify(authorRepository, times(1)).existById(AUTHOR_ID_ONE);
//        verify(authorRepository, never()).deleteById(AUTHOR_ID_ONE);
//    }
//}