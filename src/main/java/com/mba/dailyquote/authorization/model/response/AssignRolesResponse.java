package com.mba.dailyquote.authorization.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AssignRolesResponse {
    private boolean status;
}