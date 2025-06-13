package com.mba.dailyquote.author.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthorErrorCode implements ErrorCode {
    AUTHOR_NOT_FOUND("AUTHOR_0001");

    private final String code;
}
