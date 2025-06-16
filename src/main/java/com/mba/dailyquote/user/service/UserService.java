package com.mba.dailyquote.user.service;

import com.mba.dailyquote.authentication.model.entity.UserEntity;
import com.mba.dailyquote.authentication.repository.UserRepository;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.user.model.enums.UserErrorCode;
import com.mba.dailyquote.user.model.request.RequestChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseGetProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseChangeUsername changeUsername(RequestChangeUsername request, String userId) throws AppException {
        UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));

        String newUsername = request.getNewUsername();
        validateUsername(newUsername, userEntity.getId());

        userEntity.setUsername(newUsername);
        userRepository.save(userEntity);

        return ResponseChangeUsername.builder()
                .id(userEntity.getId())
                .username(newUsername)
                .build();
    }

    public ResponseGetProfile getProfile(String userId) throws AppException {
        UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));

        return ResponseGetProfile.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

    private void validateUsername(String username, Long currentUserId) throws AppException {
        boolean usernameExists = userRepository.findByUsername(username)
                .map(user -> !user.getId().equals(currentUserId))
                .orElse(false);

        if (usernameExists) {
            throw new AppException(UserErrorCode.USERNAME_ALREADY_EXISTS);
        }
    }
}