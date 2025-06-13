package com.mba.dailyquote.author.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateAuthor {
    @NotBlank(message = "{validation.author.name.notBlank}")
    private String name;
}
