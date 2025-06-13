package com.mba.dailyquote.book.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUpdateBook {
    private Long bookId;
}

