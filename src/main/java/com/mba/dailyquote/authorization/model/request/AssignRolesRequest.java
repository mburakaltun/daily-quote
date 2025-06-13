package com.mba.dailyquote.authorization.model.request;

import com.mba.dailyquote.authorization.model.enums.AuthorizationRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignRolesRequest {
    @NotNull(message = "User id must not be null or empty")
    private Long userId;

    @NotBlank(message = "Role must not be null or empty")
    private AuthorizationRole role;
}
