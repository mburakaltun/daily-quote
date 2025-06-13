package com.mba.dailyquote.author.utility;

import com.mba.dailyquote.author.model.dto.AuthorDto;
import com.mba.dailyquote.author.model.entity.AuthorEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AuthorMapper {

    public AuthorDto toDto(AuthorEntity authorEntity) {
        return AuthorDto.builder()
                .id(authorEntity.getId())
                .createdDate(authorEntity.getCreatedDate())
                .updatedDate(authorEntity.getUpdatedDate())
                .name(authorEntity.getName())
                .build();
    }

    public List<AuthorDto> toDtoList(List<AuthorEntity> authorEntityList) {
        return authorEntityList.stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }
}
