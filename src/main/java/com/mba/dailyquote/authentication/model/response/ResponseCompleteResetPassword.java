package com.mba.dailyquote.authentication.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCompleteResetPassword {
    private String userId;
}
