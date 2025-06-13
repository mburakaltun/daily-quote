package com.mba.dailyquote.book.model.response;

import com.mba.dailyquote.book.model.dto.BookDTO;
import com.mba.dailyquote.common.model.response.PageableResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseGetBooks extends PageableResponse {
    private List<BookDTO> bookDTOList;
}

