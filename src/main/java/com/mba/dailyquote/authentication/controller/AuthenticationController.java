package com.mba.dailyquote.authentication.controller;

import com.mba.dailyquote.authentication.model.request.RequestCompleteResetPassword;
import com.mba.dailyquote.authentication.model.request.RequestSignInUser;
import com.mba.dailyquote.authentication.model.request.RequestSignUpUser;
import com.mba.dailyquote.authentication.model.request.RequestStartResetPassword;
import com.mba.dailyquote.authentication.model.response.ResponseCompleteResetPassword;
import com.mba.dailyquote.authentication.model.response.ResponseSignInUser;
import com.mba.dailyquote.authentication.model.response.ResponseSignUpUser;
import com.mba.dailyquote.authentication.model.response.ResponseStartResetPassword;
import com.mba.dailyquote.authentication.service.AuthenticationService;
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
@RequestMapping("/api/v1/authentication")
public class AuthenticationController extends BaseController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<ResponseSignUpUser>> signUp(@Valid @RequestBody RequestSignUpUser requestSignUpUser) throws AppException {
        ResponseSignUpUser response = authenticationService.signUpUser(requestSignUpUser);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<ResponseSignInUser>> signIn(@Valid @RequestBody RequestSignInUser requestSignInUser) throws AppException {
        ResponseSignInUser response = authenticationService.signInUser(requestSignInUser);
        return ResponseEntity.ok(respond(response));
    }

    @PostMapping("/start-reset-password")
    public ResponseEntity<ApiResponse<ResponseStartResetPassword>> startResetPassword(@Valid @RequestBody RequestStartResetPassword requestStartResetPassword) throws AppException {
        ResponseStartResetPassword response = authenticationService.startResetPassword(requestStartResetPassword);
        return ResponseEntity.ok(respond(response));
    }

    @PostMapping("/complete-reset-password")
    public ResponseEntity<ApiResponse<ResponseCompleteResetPassword>> completeResetPassword(@Valid @RequestBody RequestCompleteResetPassword requestCompleteResetPassword) throws AppException {
        ResponseCompleteResetPassword response = authenticationService.completeResetPassword(requestCompleteResetPassword);
        return ResponseEntity.ok(respond(response));
    }
}

