package com.mba.dailyquote.book.model.response;

import com.mba.dailyquote.book.model.dto.BookDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetBook {
    private BookDto bookDto;
}

