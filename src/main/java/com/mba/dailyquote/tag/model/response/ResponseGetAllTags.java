package com.mba.dailyquote.tag.model.response;

import com.mba.dailyquote.common.model.response.PageableResponse;
import com.mba.dailyquote.tag.model.dto.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResponseGetAllTags extends PageableResponse {
    private List<TagDto> tagDtoList;
}
