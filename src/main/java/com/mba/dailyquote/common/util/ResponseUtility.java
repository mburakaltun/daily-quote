package com.mba.dailyquote.common.util;

import com.mba.dailyquote.common.model.response.ApiExceptionResponse;
import com.mba.dailyquote.common.model.response.ApiResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtility {

    public <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .build();
    }

    public ApiResponse<Void> success() {
        return ApiResponse.<Void>builder()
                .build();
    }

    public <T> ApiExceptionResponse error(String errorMessage) {
        return ApiExceptionResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }

    public <T> ApiExceptionResponse error(String errorMessage, String errorCode) {
        return ApiExceptionResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}

