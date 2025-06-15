package com.mba.dailyquote.authentication.service;

import com.mba.dailyquote.authentication.model.entity.UserEntity;
import com.mba.dailyquote.authentication.model.enums.AuthenticationErrorCode;
import com.mba.dailyquote.authentication.model.request.RequestSignInUser;
import com.mba.dailyquote.authentication.model.request.RequestSignUpUser;
import com.mba.dailyquote.authentication.model.response.ResponseSignInUser;
import com.mba.dailyquote.authentication.model.response.ResponseSignUpUser;
import com.mba.dailyquote.authentication.repository.UserRepository;
import com.mba.dailyquote.authorization.model.enums.AuthorizationRole;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.util.JwtUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseSignUpUser signUpUser(RequestSignUpUser requestSignUpUser) throws AppException {
        String email = requestSignUpUser.getEmail();
        String username = requestSignUpUser.getUsername();
        String password = requestSignUpUser.getPassword();

        validateEmail(email);
        validateUsername(username);
        validatePasswordMatch(requestSignUpUser);

        String encodedPassword = passwordEncoder.encode(password);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername(username);
        userEntity.setEncodedPassword(encodedPassword);
        userEntity.setRole(AuthorizationRole.ROLE_STANDARD_USER);
        userRepository.save(userEntity);

        return ResponseSignUpUser.builder()
                .id(userEntity.getId())
                .build();
    }

    public ResponseSignInUser signInUser(RequestSignInUser requestSignInUser) throws AppException {
        String email = requestSignInUser.getEmail();
        String password = requestSignInUser.getPassword();

        UserEntity userEntity = validateUserCredentials(email, password);
        Authentication authentication = authenticateUser(email, password);

        AuthorizationRole role = generateRole(authentication);
        String authenticationToken = JwtUtility.generateToken(email, role);

        return ResponseSignInUser.builder()
                .userId(String.valueOf(userEntity.getId()))
                .username(userEntity.getUsername())
                .authenticationToken(authenticationToken)
                .build();
    }

    private AuthorizationRole generateRole(Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        return AuthorizationRole.valueOf(role);
    }

    private Authentication authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private void validateEmail(String email) throws AppException {
        boolean isEmailExist = userRepository.existsByEmail(email);
        if (isEmailExist) {
            throw new AppException(AuthenticationErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private void validateUsername(String username) throws AppException {
        boolean isUsernameExist = userRepository.existsByUsername(username);
        if (isUsernameExist) {
            throw new AppException(AuthenticationErrorCode.USERNAME_ALREADY_EXISTS);
        }
    }

    private UserEntity validateUserCredentials(String email, String password) throws AppException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new AppException(AuthenticationErrorCode.INVALID_CREDENTIALS));

        String encodedPassword = userEntity.getEncodedPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, encodedPassword);
        if (!isPasswordCorrect) {
            throw new AppException(AuthenticationErrorCode.INVALID_CREDENTIALS);
        }
        return userEntity;
    }

    private void validatePasswordMatch(RequestSignUpUser requestSignUpUser) throws AppException {
        String password = requestSignUpUser.getPassword();
        String confirmPassword = requestSignUpUser.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new AppException(AuthenticationErrorCode.PASSWORD_MISMATCH);
        }
    }
}