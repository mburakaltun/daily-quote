package com.mba.dailyquote.tag.utility;

import com.mba.dailyquote.tag.model.dto.TagDto;
import com.mba.dailyquote.tag.model.entity.TagEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TagMapper {
    public TagDto toDto(TagEntity tagEntity) {
        return TagDto.builder()
                .id(tagEntity.getId())
                .name(tagEntity.getName())
                .build();
    }

    public List<TagDto> toDtoList(List<TagEntity> tagEntities) {
        return tagEntities.stream()
                .map(TagMapper::toDto)
                .toList();
    }
}
