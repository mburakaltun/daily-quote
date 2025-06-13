package com.mba.dailyquote.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
