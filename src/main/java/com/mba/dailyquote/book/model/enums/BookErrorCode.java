package com.mba.dailyquote.book.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BookErrorCode implements ErrorCode {
    BOOK_NOT_FOUND("BOOK_0001");

    private final String code;
}
