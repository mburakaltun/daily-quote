package com.mba.dailyquote.quoteCategory.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCreateQuoteCategory {
    private Long quoteCategoryId;
}
