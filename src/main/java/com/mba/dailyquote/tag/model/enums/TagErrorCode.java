package com.mba.dailyquote.tag.model.enums;

import com.mba.dailyquote.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TagErrorCode implements ErrorCode {
    TAG_NOT_FOUND("TAG_0001"),;

    private final String code;
}
