package com.mba.dailyquote.quote.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUpdateQuote {
    private Long quoteId;
}
