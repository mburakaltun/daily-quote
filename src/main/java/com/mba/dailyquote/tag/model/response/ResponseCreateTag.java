package com.mba.dailyquote.tag.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCreateTag {
    private Long tagId;
}
