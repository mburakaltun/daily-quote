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
    @NotBlank(message = "{validation.tag.name.notBlank}")
    private String name;
}
