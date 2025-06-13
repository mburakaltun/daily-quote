package com.mba.dailyquote.authorization.controller;

import com.mba.dailyquote.authorization.model.request.AssignRolesRequest;
import com.mba.dailyquote.authorization.model.response.AssignRolesResponse;
import com.mba.dailyquote.authorization.service.AuthorizationService;
import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authorization")
public class AuthorizationController extends BaseController {

    private final AuthorizationService authorizationService;

    @PostMapping("/assignRoles")
    public ResponseEntity<ApiResponse<AssignRolesResponse>> assignRoles(@Valid @RequestBody AssignRolesRequest assignRolesRequest) throws AppException {
        AssignRolesResponse response = authorizationService.assignRoles(assignRolesRequest);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }
}
