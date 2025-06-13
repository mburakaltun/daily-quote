package com.mba.dailyquote.quoteType.model.response;

import com.mba.dailyquote.quoteType.model.dto.QuoteTypeDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetQuoteType {
    private QuoteTypeDto quoteTypeDto;
}
