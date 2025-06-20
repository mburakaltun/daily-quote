package com.mba.dailyquote.authorization.service;

import com.mba.dailyquote.authentication.model.entity.UserEntity;
import com.mba.dailyquote.authentication.model.enums.AuthenticationErrorCode;
import com.mba.dailyquote.authorization.model.enums.AuthorizationRole;
import com.mba.dailyquote.user.repository.UserJpaRepository;
import com.mba.dailyquote.authorization.model.request.AssignRolesRequest;
import com.mba.dailyquote.authorization.model.response.AssignRolesResponse;
import com.mba.dailyquote.common.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UserJpaRepository userJpaRepository;

    public AssignRolesResponse assignRoles(AssignRolesRequest assignRolesRequest) throws AppException {
        Long userId = assignRolesRequest.getUserId();
        AuthorizationRole role = assignRolesRequest.getRole();

        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new AppException(AuthenticationErrorCode.USER_NOT_FOUND));


        userEntity.setRole(role);
        userJpaRepository.save(userEntity);

        return AssignRolesResponse.builder()
                .status(Boolean.TRUE)
                .build();
    }
}