package com.mba.dailyquote.authentication.model.entity;

import com.mba.dailyquote.common.model.entity.BaseStatusEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_reset_token")
@SQLRestriction("status != -1")
public class PasswordResetTokenEntity extends BaseStatusEntity {
    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity userEntity;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime expiryDate;
}
