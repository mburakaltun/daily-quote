package com.mba.dailyquote.quoteType.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCreateQuoteType {
    private Long quoteTypeId;
}
