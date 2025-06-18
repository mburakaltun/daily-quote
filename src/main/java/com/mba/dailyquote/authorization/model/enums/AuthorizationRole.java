package com.mba.dailyquote.authorization.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthorizationRole {
    ROLE_STANDARD_USER(1),
    ROLE_ADMIN(720);

    private final int expirationTimeInHours;
}
