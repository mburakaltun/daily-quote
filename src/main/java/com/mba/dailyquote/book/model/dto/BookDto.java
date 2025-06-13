package com.mba.dailyquote.book.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookDto {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String title;
}
