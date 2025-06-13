package com.mba.dailyquote.quote.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuoteErrorCode implements ErrorCode {
    QUOTE_NOT_FOUND("QUOTE_0001");

    private final String code;
}
