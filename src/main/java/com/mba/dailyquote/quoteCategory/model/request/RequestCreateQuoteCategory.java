package com.mba.dailyquote.quoteCategory.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateQuoteCategory {
    @NotBlank(message = "{validation.quoteCategory.name.notBlank}")
    private String name;
}
