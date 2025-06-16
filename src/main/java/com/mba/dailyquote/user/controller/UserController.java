package com.mba.dailyquote.user.controller;

import com.mba.dailyquote.user.model.request.RequestChangePassword;
import com.mba.dailyquote.user.model.response.ResponseChangePassword;
import com.mba.dailyquote.common.constants.AppHeaders;
import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.user.model.request.RequestChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseDeleteUser;
import com.mba.dailyquote.user.model.response.ResponseGetProfile;
import com.mba.dailyquote.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ResponseGetProfile>> getProfile(@RequestHeader(AppHeaders.X_USER_ID) String userId) throws AppException {
        ResponseGetProfile response = userService.getProfile(userId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @PutMapping("/username")
    public ResponseEntity<ApiResponse<ResponseChangeUsername>> changeUsername(@RequestHeader(AppHeaders.X_USER_ID) String userId,
                                                                              @Valid @RequestBody RequestChangeUsername requestChangeUsername) throws AppException {
        ResponseChangeUsername response = userService.changeUsername(requestChangeUsername, userId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse<ResponseChangePassword>> changePassword(@RequestHeader(AppHeaders.X_USER_ID) String userId,
                                                                              @Valid @RequestBody RequestChangePassword requestChangePassword) throws AppException {
        ResponseChangePassword response = userService.changePassword(requestChangePassword, userId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<ResponseDeleteUser>> deleteUser(@RequestHeader(AppHeaders.X_USER_ID) String userId) throws AppException {
        ResponseDeleteUser response = userService.deleteUser(userId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }
}