package com.mjc.school.service.impl;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.dto.TagFilter;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validation.annotation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper mapper) {
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TagResponseDto> readAll(int page, int size, String sortBy) {
        var tagsList =tagRepository.readAll(page, size, sortBy);
        return mapper.listTagsToTagResponseDto(tagsList);
    }

    @Validate(value = "checkTagId")
    @Override
    public TagResponseDto readById(Long id) {
        if (tagRepository.existById(id)) {
            return mapper.mapTagToTagResponseDto(tagRepository.readById(id).get());
        } else {
            throw new NotFoundException(String.format("Tag with ID %d not found.", id));
        }
    }

    @Validate(value = "checkTag")
    @Override
    public TagResponseDto create(TagRequestDto tagRequestDto) {
        var tag = mapper.mapTagRequestDtoToTag(tagRequestDto);
        var createdTag = tagRepository.create(tag);
        return mapper.mapTagToTagResponseDto(createdTag);
    }

    @Validate(value = "checkTag")
    @Override
    public TagResponseDto update(TagRequestDto tagRequestDto) {
        if (tagRepository.existById(tagRequestDto.getId())) {
            var tag = mapper.mapTagRequestDtoToTag(tagRequestDto);
            tag.setName(tagRequestDto.getName());
            return mapper.mapTagToTagResponseDto(tagRepository.update(tag));
        } else {
            throw new NotFoundException(String.format("Tag with ID %d not found.", tagRequestDto.getId()));
        }
    }

    @Validate(value = "checkTagId")
    @Override
    public boolean deleteById(Long id) {
        if (tagRepository.existById(id)) {
            return tagRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Tag with ID %d not found.", id));
        }
    }

    @Override
    public List<TagResponseDto> readByNewsId(Long id) {
        List<Tag> tags = tagRepository.readByNewsId(id);
        return tags.stream()
                .map(mapper::mapTagToTagResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Tag> getComments(TagFilter params, Pageable pageable) {
        return null;
    }
}