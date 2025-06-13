package com.mba.dailyquote.quote.model.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class QuoteDto {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isActive;
    private String content;
    private String contentTr;
    private String note;
    private String authorName;
    private String quoteCategoryName;
    private String quoteTypeName;
    private String bookTitle;
    private String source;
    private List<String> tagList;
}
