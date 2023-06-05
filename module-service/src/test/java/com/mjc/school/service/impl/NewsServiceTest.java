//package com.mjc.school.service.impl;
//
//import com.mjc.school.repository.impl.NewsRepositoryImpl;
//import com.mjc.school.repository.model.Author;
//import com.mjc.school.repository.model.News;
//import com.mjc.school.service.dto.AuthorResponseDto;
//import com.mjc.school.service.dto.NewsRequestDto;
//import com.mjc.school.service.dto.NewsResponseDto;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.mapper.NewsMapper;
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
//import static com.mjc.school.service.constant.Constant.AUTHOR_NAME;
//import static com.mjc.school.service.constant.Constant.NEWS_CONTENT;
//import static com.mjc.school.service.constant.Constant.NEWS_ID_ONE;
//import static com.mjc.school.service.constant.Constant.NEWS_ID_TWO;
//import static com.mjc.school.service.constant.Constant.NEWS_TITLE;
//import static com.mjc.school.service.constant.Constant.NEWS_UPDATED_TITLE;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class NewsServiceTest {
//
//    @Mock
//    private NewsRepositoryImpl newsRepository;
//
//    @Mock
//    private NewsMapper mapper;
//
//    @InjectMocks
//    private NewsServiceImpl newsService;
//
//    LocalDateTime now = LocalDateTime.now();
//
//    @Test
//    void readAll() {
//        var author = new Author();
//        author.setId(1L);
//        author.setName("Author name");
//        author.setCreateDate(now);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now);
//
//        var news = new News();
//        news.setId(NEWS_ID_ONE);
//        news.setTitle(NEWS_TITLE);
//        news.setContent(NEWS_CONTENT);
//        news.setAuthor(author);
//        news.setCreateDate(now);
//
//        List<News> newsList = new ArrayList<>();
//        newsList.add(new News(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, now, now, author));
//        newsList.add(new News(NEWS_ID_TWO, NEWS_UPDATED_TITLE, NEWS_CONTENT, now, now, author));
//
//        List<NewsResponseDto> expectedDtos = new ArrayList<>();
//        expectedDtos.add(new NewsResponseDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L));
//        expectedDtos.add(new NewsResponseDto(NEWS_ID_TWO, NEWS_UPDATED_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L));
//
//        when(newsRepository.readAll()).thenReturn(newsList);
//        when(mapper.mapNewsToNewsResponseDtoList(newsList)).thenReturn(expectedDtos);
//
//        var actualResult = newsService.readAll();
//
//        assertEquals(expectedDtos, actualResult);
//        verify(newsRepository).readAll();
//        verify(mapper).mapNewsToNewsResponseDtoList(newsList);
//    }
//
//    @Test
//    void readById() {
//        var author = new Author();
//        author.setId(AUTHOR_ID_ONE);
//        author.setName("Author name");
//        author.setCreateDate(now);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now);
//
//        var news = new News();
//        news.setId(NEWS_ID_ONE);
//        news.setTitle(NEWS_TITLE);
//        news.setContent(NEWS_CONTENT);
//        news.setAuthor(author);
//        news.setCreateDate(now);
//        var expectedDto = new NewsResponseDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L);
//
//        when(newsRepository.existById(NEWS_ID_ONE)).thenReturn(true);
//        when(newsRepository.readById(NEWS_ID_ONE)).thenReturn(Optional.of(news));
//        when(mapper.mapNewsToNewsResponseDto(news)).thenReturn(expectedDto);
//
//        NewsResponseDto actualDto = newsService.readById(NEWS_ID_ONE);
//
//        assertEquals(expectedDto, actualDto);
//        verify(newsRepository).existById(NEWS_ID_ONE);
//        verify(newsRepository).readById(NEWS_ID_ONE);
//        verify(mapper).mapNewsToNewsResponseDto(news);
//    }
//
//    @Test
//    void create() {
//        var author = new Author();
//        author.setId(AUTHOR_ID_ONE);
//        author.setName(AUTHOR_NAME);
//        author.setCreateDate(now);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now);
//
//        var news = new News();
//        news.setId(NEWS_ID_ONE);
//        news.setTitle(NEWS_TITLE);
//        news.setContent(NEWS_CONTENT);
//        news.setAuthor(author);
//        news.setCreateDate(now);
//        var newsRequestDto = new NewsRequestDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, author.getId(), 1L,1L);
//        var newsResponseDto = new NewsResponseDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L,1L);
//
//        when(newsRepository.existById(anyLong())).thenReturn(true);
//        when(mapper.mapNewsRequestDtoToNews(any(NewsRequestDto.class))).thenReturn(news);
//        when(newsRepository.create(any(News.class))).thenReturn(news);
//        when(mapper.mapNewsToNewsResponseDto(any(News.class))).thenReturn(newsResponseDto);
//
//        NewsResponseDto result = newsService.create(newsRequestDto);
//
//        assertEquals(new NewsResponseDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L), result);
//        verify(mapper, times(1)).mapNewsRequestDtoToNews(newsRequestDto);
//        verify(newsRepository, times(1)).create(news);
//        verify(mapper, times(1)).mapNewsToNewsResponseDto(news);
//    }
//
//    @Test
//    void update() {
//        var author = new Author();
//        author.setId(AUTHOR_ID_ONE);
//        author.setName(AUTHOR_NAME);
//        author.setCreateDate(now);
//        var authorResponseDto = new AuthorResponseDto(AUTHOR_ID_ONE, AUTHOR_NAME, now, now);
//
//        var news = new News();
//        news.setId(NEWS_ID_ONE);
//        news.setTitle(NEWS_TITLE);
//        news.setContent(NEWS_CONTENT);
//        news.setAuthor(author);
//        news.setCreateDate(now);
//        var updatedNews = new News();
//        updatedNews.setId(NEWS_ID_ONE);
//        updatedNews.setTitle(NEWS_TITLE);
//        updatedNews.setContent(NEWS_CONTENT);
//        updatedNews.setAuthor(author);
//        updatedNews.setCreateDate(now);
//        var newsRequestDto = new NewsRequestDto(NEWS_ID_ONE, NEWS_TITLE, NEWS_CONTENT, author.getId(), 1L);
//        var newsResponseDto = new NewsResponseDto(NEWS_ID_ONE, NEWS_UPDATED_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L);
//
//        when(newsRepository.existById(anyLong())).thenReturn(true);
//        when(mapper.mapNewsRequestDtoToNews(any(NewsRequestDto.class))).thenReturn(news);
//        when(newsRepository.update(any(News.class))).thenReturn(updatedNews);
//        when(mapper.mapNewsToNewsResponseDto(any(News.class))).thenReturn(newsResponseDto);
//
//        NewsResponseDto result = newsService.update(newsRequestDto);
//
//        assertEquals(new NewsResponseDto(NEWS_ID_ONE, NEWS_UPDATED_TITLE, NEWS_CONTENT, now, now, authorResponseDto, 1L), result);
//        verify(mapper, times(1)).mapNewsRequestDtoToNews(newsRequestDto);
//        verify(mapper, times(1)).mapNewsToNewsResponseDto(updatedNews);
//    }
//
//    @Test
//    void testDeleteById() {
//        when(newsRepository.existById(NEWS_ID_ONE)).thenReturn(true);
//        when(newsRepository.deleteById(NEWS_ID_ONE)).thenReturn(true);
//
//        boolean deleted = newsService.deleteById(NEWS_ID_ONE);
//
//        assertTrue(deleted);
//        verify(newsRepository).existById(NEWS_ID_ONE);
//        verify(newsRepository).deleteById(NEWS_ID_ONE);
//    }
//
//    @Test
//    void testDeleteByIdNonExisting() {
//        when(newsRepository.existById(NEWS_ID_ONE)).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> newsService.deleteById(NEWS_ID_ONE));
//        verify(newsRepository, times(1)).existById(NEWS_ID_ONE);
//        verify(newsRepository, never()).deleteById(NEWS_ID_ONE);
//    }
//}
