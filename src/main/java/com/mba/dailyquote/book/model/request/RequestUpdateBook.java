package com.mba.dailyquote.book.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateBook {
    @NotBlank(message = "{validation.book.title.notBlank}")
    private String title;
}
