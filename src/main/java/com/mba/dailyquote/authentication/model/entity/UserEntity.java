package com.mba.dailyquote.authentication.model.entity;

import com.mba.dailyquote.authorization.model.enums.AuthorizationRole;
import com.mba.dailyquote.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Enumerated(EnumType.STRING)
    private AuthorizationRole role;
}
