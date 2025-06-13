package com.mba.dailyquote.author.model.response;

import com.mba.dailyquote.author.model.dto.AuthorDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetAuthor {
    private AuthorDTO authorDTO;
}
