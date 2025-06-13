package com.mba.dailyquote.quoteCategory.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuoteCategoryErrorCode implements ErrorCode {
    QUOTE_CATEGORY_NOT_FOUND("QUOTE_CATEGORY_0001");

    private final String code;
}
