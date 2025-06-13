package com.mba.dailyquote.quoteType.model.response;

import com.mba.dailyquote.common.model.response.PageableResponse;
import com.mba.dailyquote.quoteType.model.dto.QuoteTypeDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseGetAllQuoteTypes extends PageableResponse {
    private List<QuoteTypeDto> quoteTypeDtoList;
}
