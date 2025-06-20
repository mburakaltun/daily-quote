package com.mba.dailyquote.authentication.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestStartResetPassword {
    @Email(message = "{validation.email.invalid}")
    @NotBlank(message = "{validation.email.notBlank}")
    private String email;
}
