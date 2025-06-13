package com.mba.dailyquote.quoteCategory.model.request;

import com.mba.dailyquote.common.model.request.PageableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class RequestGetAllQuoteCategories extends PageableRequest {
}
