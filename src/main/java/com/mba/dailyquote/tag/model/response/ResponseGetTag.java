package com.mba.dailyquote.tag.model.response;

import com.mba.dailyquote.tag.model.dto.TagDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGetTag {
    private TagDto tagDto;
}
