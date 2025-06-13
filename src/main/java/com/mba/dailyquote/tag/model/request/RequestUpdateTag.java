package com.mba.dailyquote.tag.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateTag {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}
