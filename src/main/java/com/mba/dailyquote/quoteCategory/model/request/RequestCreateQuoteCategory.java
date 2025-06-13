package com.mba.dailyquote.quoteCategory.model.request;

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

    @NotNull(message = "Name cannot be null")
    private String name;
}
