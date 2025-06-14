package com.mba.dailyquote.quoteType.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateQuoteType {
    @NotBlank(message = "{validation.quoteType.name.notBlank}")
    private String name;
}
