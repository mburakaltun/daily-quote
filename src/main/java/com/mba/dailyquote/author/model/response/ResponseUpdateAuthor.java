package com.mba.dailyquote.author.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUpdateAuthor {
    private Long authorId;
}
