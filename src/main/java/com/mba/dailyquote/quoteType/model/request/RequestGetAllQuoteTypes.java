package com.mba.dailyquote.quoteType.model.request;

import com.mba.dailyquote.common.model.request.PageableRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RequestGetAllQuoteTypes extends PageableRequest {
}
