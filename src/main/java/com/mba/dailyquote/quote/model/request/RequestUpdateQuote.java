package com.mba.dailyquote.quote.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateQuote {
    private Long id;

    @Size(min = 5, max = 255, message = "Quote must be between 5 and 255 characters")
    private String content;

    @Size(min = 5, max = 255, message = "Turkish Translation of Quote must be between 5 and 255 characters")
    private String contentTr;

    @Size(max = 255, message = "Note must be less than 255 characters")
    private String note;

    private boolean isActive;

    private String tagList;

    private Long authorId;
    private Long quoteCategoryId;
    private Long quoteTypeId;
    private Long bookId;

    private String authorName;
    private String quoteCategoryName;
    private String quoteTypeName;
    private String bookTitle;
    private String source;
}