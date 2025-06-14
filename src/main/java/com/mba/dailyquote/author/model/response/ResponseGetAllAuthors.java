package com.mba.dailyquote.author.model.response;

import com.mba.dailyquote.author.model.dto.AuthorDto;
import com.mba.dailyquote.common.model.response.PageableResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class ResponseGetAllAuthors extends PageableResponse {
    private List<AuthorDto> authorDtoList;
}
