package com.mba.dailyquote.quoteCategory.model.response;

import com.mba.dailyquote.quoteCategory.model.dto.QuoteCategoryDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetQuoteCategory {
    private QuoteCategoryDto quoteCategoryDto;
}
