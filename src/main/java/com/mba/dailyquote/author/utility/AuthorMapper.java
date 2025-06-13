package com.mba.dailyquote.author.utility;

import com.mba.dailyquote.author.model.dto.AuthorDTO;
import com.mba.dailyquote.author.model.entity.AuthorEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AuthorMapper {

    public AuthorDTO toDto(AuthorEntity authorEntity) {
        return AuthorDTO.builder()
                .id(authorEntity.getId())
                .createdDate(authorEntity.getCreatedDate())
                .updatedDate(authorEntity.getUpdatedDate())
                .name(authorEntity.getName())
                .build();
    }

    public List<AuthorDTO> toDtoList(List<AuthorEntity> authorEntityList) {
        return authorEntityList.stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }
}
