package com.mba.dailyquote.quoteType.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuoteTypeDto {
    public Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    public String name;
}
