package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.dto.TagResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T18:53:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class TagMapperImpl extends TagMapper {

    @Override
    public Tag mapTagRequestDtoToTag(TagRequestDto tagDtoRequest) {
        if ( tagDtoRequest == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDtoRequest.getId() );
        tag.setName( tagDtoRequest.getName() );

        return tag;
    }

    @Override
    public TagResponseDto mapTagToTagResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = tag.getId();
        name = tag.getName();

        TagResponseDto tagResponseDto = new TagResponseDto( id, name );

        return tagResponseDto;
    }

    @Override
    public List<TagResponseDto> listTagsToTagResponseDto(List<Tag> tagModel) {
        if ( tagModel == null ) {
            return null;
        }

        List<TagResponseDto> list = new ArrayList<TagResponseDto>( tagModel.size() );
        for ( Tag tag : tagModel ) {
            list.add( mapTagToTagResponseDto( tag ) );
        }

        return list;
    }
}
