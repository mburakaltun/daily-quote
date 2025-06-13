package com.mba.dailyquote.quote.model.response;

import com.mba.dailyquote.quote.model.dto.QuoteDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetQuote {
    private QuoteDto quoteDto;
}
