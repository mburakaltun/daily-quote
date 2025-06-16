package com.mba.dailyquote.user.service;

import com.mba.dailyquote.authentication.model.entity.UserEntity;
import com.mba.dailyquote.authentication.repository.UserJpaRepository;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.enums.Status;
import com.mba.dailyquote.user.model.enums.UserErrorCode;
import com.mba.dailyquote.user.model.request.RequestChangePassword;
import com.mba.dailyquote.user.model.request.RequestChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseChangePassword;
import com.mba.dailyquote.user.model.response.ResponseChangeUsername;
import com.mba.dailyquote.user.model.response.ResponseDeleteUser;
import com.mba.dailyquote.user.model.response.ResponseGetProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public ResponseChangeUsername changeUsername(RequestChangeUsername request, String userId) throws AppException {
        UserEntity userEntity = userJpaRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));

        String newUsername = request.getNewUsername();
        validateUsername(newUsername, userEntity.getId());

        userEntity.setUsername(newUsername);
        userJpaRepository.save(userEntity);

        return ResponseChangeUsername.builder()
                .id(userEntity.getId())
                .username(newUsername)
                .build();
    }

    public ResponseGetProfile getProfile(String userId) throws AppException {
        UserEntity userEntity = userJpaRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));

        return ResponseGetProfile.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

    public ResponseChangePassword changePassword(RequestChangePassword requestChangePassword, String userId) throws AppException {
        if (!requestChangePassword.getNewPassword().equals(requestChangePassword.getConfirmNewPassword())) {
            throw new AppException(UserErrorCode.PASSWORD_MISMATCH);
        }

        UserEntity userEntity = userJpaRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));
        boolean isPasswordCorrect = passwordEncoder.matches(requestChangePassword.getCurrentPassword(), userEntity.getEncodedPassword());

        if (!isPasswordCorrect) {
            throw new AppException(UserErrorCode.PASSWORD_INCORRECT);
        }

        userEntity.setEncodedPassword(passwordEncoder.encode(requestChangePassword.getNewPassword()));
        userJpaRepository.save(userEntity);

        return ResponseChangePassword.builder()
                .userId(userEntity.getId())
                .build();
    }

    @Transactional
    public ResponseDeleteUser deleteUser(String userId) throws AppException {
        UserEntity userEntity = userJpaRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_FOUND));

        userEntity.setStatus(Status.DELETED);
        userJpaRepository.save(userEntity);

        return ResponseDeleteUser.builder()
                .userId(Long.valueOf(userId))
                .build();
    }

    private void validateUsername(String username, Long currentUserId) throws AppException {
        boolean usernameExists = userJpaRepository.findByUsername(username)
                .map(user -> !user.getId().equals(currentUserId))
                .orElse(false);

        if (usernameExists) {
            throw new AppException(UserErrorCode.USERNAME_ALREADY_EXISTS);
        }
    }
}