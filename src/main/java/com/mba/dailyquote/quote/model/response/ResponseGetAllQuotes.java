package com.mba.dailyquote.quote.model.response;

import com.mba.dailyquote.common.model.response.PageableResponse;
import com.mba.dailyquote.quote.model.dto.QuoteDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseGetAllQuotes extends PageableResponse {
    private List<QuoteDto> quoteDtoList;
}
