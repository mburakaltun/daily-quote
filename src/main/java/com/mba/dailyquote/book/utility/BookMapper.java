package com.mba.dailyquote.book.utility;

import com.mba.dailyquote.book.model.dto.BookDto;
import com.mba.dailyquote.book.model.entity.BookEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookMapper {

    public BookDto toDto(BookEntity bookEntity) {
        return BookDto.builder()
                .id(bookEntity.getId())
                .createdDate(bookEntity.getCreatedDate())
                .updatedDate(bookEntity.getUpdatedDate())
                .title(bookEntity.getTitle())
                .build();
    }

    public List<BookDto> toDtoList(List<BookEntity> bookEntityList) {
        return bookEntityList.stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }
}
