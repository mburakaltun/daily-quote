package com.mba.dailyquote.book.utility;

import com.mba.dailyquote.book.model.dto.BookDTO;
import com.mba.dailyquote.book.model.entity.BookEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookMapper {

    public BookDTO toDto(BookEntity bookEntity) {
        return BookDTO.builder()
                .id(bookEntity.getId())
                .createdDate(bookEntity.getCreatedDate())
                .updatedDate(bookEntity.getUpdatedDate())
                .title(bookEntity.getTitle())
                .build();
    }

    public List<BookDTO> toDtoList(List<BookEntity> bookEntityList) {
        return bookEntityList.stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }
}
