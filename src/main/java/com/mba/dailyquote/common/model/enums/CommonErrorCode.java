package com.mba.dailyquote.common.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {
    USER_NOT_FOUND("COMMON_0001"),;

    private final String code;
}
