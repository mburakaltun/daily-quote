package com.mba.dailyquote.quoteCategory.utility;

import com.mba.dailyquote.quoteCategory.model.dto.QuoteCategoryDto;
import com.mba.dailyquote.quoteCategory.model.entity.QuoteCategoryEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class QuoteCategoryMapper {
    public QuoteCategoryDto toDto(QuoteCategoryEntity quoteCategoryEntity) {
        return QuoteCategoryDto.builder()
                .id(quoteCategoryEntity.getId())
                .name(quoteCategoryEntity.getName())
                .build();
    }

    public List<QuoteCategoryDto> toDtoList(List<QuoteCategoryEntity> quoteCategoryEntityList) {
        return quoteCategoryEntityList.stream()
                .map(QuoteCategoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
