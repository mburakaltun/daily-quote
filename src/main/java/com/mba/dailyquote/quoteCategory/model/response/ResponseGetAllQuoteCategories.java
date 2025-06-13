package com.mba.dailyquote.quoteCategory.model.response;

import com.mba.dailyquote.common.model.response.PageableResponse;
import com.mba.dailyquote.quoteCategory.model.dto.QuoteCategoryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseGetAllQuoteCategories extends PageableResponse {
    private List<QuoteCategoryDto> quoteCategoryDtoList;
}
