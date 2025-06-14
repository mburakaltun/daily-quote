package com.mba.dailyquote.quote.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateQuote {
    @NotBlank(message = "{validation.quote.content.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.quote.content.size}")
    private String content;

    @NotBlank(message = "{validation.quote.contentTr.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.quote.contentTr.size}")
    private String contentTr;

    @NotBlank(message = "{validation.quote.note.notBlank}")
    @Size(min = 3, max = 255, message = "{validation.quote.note.size}")
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