//package com.mjc.school.service.impl;
//
//import com.mjc.school.repository.impl.TagRepositoryImpl;
//import com.mjc.school.repository.model.Tag;
//import com.mjc.school.service.dto.TagRequestDto;
//import com.mjc.school.service.dto.TagResponseDto;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.mapper.TagMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.mjc.school.service.constant.Constant.TAG_ID_ONE;
//import static com.mjc.school.service.constant.Constant.TAG_ID_TWO;
//import static com.mjc.school.service.constant.Constant.TAG_NAME;
//import static com.mjc.school.service.constant.Constant.TAG_UPDATED_NAME;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TagServiceTest {
//
//    @Mock
//    private TagMapper mapper;
//
//    @Mock
//    private TagRepositoryImpl tagRepository;
//
//    @InjectMocks
//    private TagServiceImpl tagService;
//
//    @Test
//    void readAll() {
//        List<Tag> tags = new ArrayList<>();
//        tags.add(new Tag(TAG_ID_ONE, TAG_NAME));
//        tags.add(new Tag(TAG_ID_TWO, TAG_UPDATED_NAME));
//
//        List<TagResponseDto> tagResponseDto = new ArrayList<>();
//        tagResponseDto.add(new TagResponseDto(TAG_ID_ONE, TAG_NAME));
//        tagResponseDto.add(new TagResponseDto(TAG_ID_TWO, TAG_UPDATED_NAME));
//
//        when(tagRepository.readAll()).thenReturn(tags);
//        when(mapper.listTagsToTagResponseDto(tags)).thenReturn(tagResponseDto);
//
//        var result = tagService.readAll();
//
//        assertEquals(tagResponseDto, result);
//    }
//
//    @Test
//    void readById() {
//        var tag = new Tag();
//        tag.setId(TAG_ID_ONE);
//        tag.setName(TAG_NAME);
//        var tagResponseDto = new TagResponseDto(TAG_ID_ONE, TAG_NAME);
//
//        when(tagRepository.existById(anyLong())).thenReturn(true);
//        when(tagRepository.readById(anyLong())).thenReturn(Optional.of(tag));
//        when(mapper.mapTagToTagResponseDto(tag)).thenReturn(tagResponseDto);
//
//        TagResponseDto result = tagService.readById(TAG_ID_ONE);
//
//        assertEquals(tagResponseDto, result);
//    }
//
//    @Test
//    void create() throws NotFoundException {
//        var tagDto = new TagRequestDto(TAG_ID_ONE, TAG_NAME);
//        var tag = new Tag(TAG_ID_ONE, TAG_NAME);
//        var savedTag = new Tag(TAG_ID_ONE, TAG_NAME);
//
//        when(mapper.mapTagRequestDtoToTag(tagDto)).thenReturn(tag);
//        when(tagRepository.create(any(Tag.class))).thenReturn(savedTag);
//        when(mapper.mapTagToTagResponseDto(savedTag)).thenReturn(new TagResponseDto(TAG_ID_ONE, TAG_NAME));
//
//        var result = tagService.create(tagDto);
//
//        assertEquals(new TagResponseDto(TAG_ID_ONE, TAG_NAME), result);
//        verify(mapper, times(1)).mapTagRequestDtoToTag(tagDto);
//        verify(tagRepository, times(1)).create(tag);
//        verify(mapper, times(1)).mapTagToTagResponseDto(savedTag);
//
//    }
//
//    @Test
//    void update() throws NotFoundException {
//        var tag = new Tag();
//        tag.setId(TAG_ID_ONE);
//        tag.setName(TAG_NAME);
//
//        var updatedTag = new Tag();
//        updatedTag.setId(TAG_ID_ONE);
//        updatedTag.setName(TAG_NAME);
//
//        var tagRequestDto = new TagRequestDto(TAG_ID_ONE, TAG_NAME);
//        var tagResponseDto = new TagResponseDto(TAG_ID_ONE, TAG_UPDATED_NAME);
//
//        when(tagRepository.existById(TAG_ID_ONE)).thenReturn(true);
//        when(mapper.mapTagRequestDtoToTag(tagRequestDto)).thenReturn(tag);
//        when(tagRepository.update(any(Tag.class))).thenReturn(updatedTag);
//        when(mapper.mapTagToTagResponseDto(any(Tag.class))).thenReturn(tagResponseDto);
//
//        var result = tagService.update(tagRequestDto);
//
//        assertEquals(tagResponseDto, result);
//    }
//
//    @Test
//    void deleteById() {
//        when(tagRepository.existById(TAG_ID_ONE)).thenReturn(true);
//        when(tagRepository.deleteById(TAG_ID_ONE)).thenReturn(true);
//
//        var deleted = tagService.deleteById(TAG_ID_ONE);
//
//        assertTrue(deleted);
//        verify(tagRepository, times(1)).existById(TAG_ID_ONE);
//        verify(tagRepository, times(1)).deleteById(TAG_ID_ONE);
//    }
//
//    @Test
//    void deleteByIdNonExisting() {
//        when(tagRepository.existById(TAG_ID_ONE)).thenReturn(false);
//
//        assertThrows(NotFoundException.class, () -> tagService.deleteById(TAG_ID_ONE));
//        verify(tagRepository, times(1)).existById(TAG_ID_ONE);
//        verify(tagRepository, never()).deleteById(TAG_ID_ONE);
//    }
//}