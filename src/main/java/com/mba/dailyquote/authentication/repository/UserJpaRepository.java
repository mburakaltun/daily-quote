package com.mba.dailyquote.authentication.repository;

import com.mba.dailyquote.authentication.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
