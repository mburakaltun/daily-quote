package com.mba.dailyquote.authentication.repository;

import com.mba.dailyquote.authentication.model.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenJpaRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    Optional<PasswordResetTokenEntity> findByUserEntity_Id(Long userEntityId);

    Optional<PasswordResetTokenEntity> findByToken(String token);
}
