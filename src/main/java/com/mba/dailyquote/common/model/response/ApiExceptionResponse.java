package com.mba.dailyquote.common.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiExceptionResponse {
    private String errorCode;
    private String errorMessage;
}
