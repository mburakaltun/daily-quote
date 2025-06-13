package com.mba.dailyquote.quoteType.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuoteTypeErrorCode implements ErrorCode {
    QUOTE_TYPE_NOT_FOUND("QUOTE_TYPE_0001");

    private final String code;
}