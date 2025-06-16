package com.mba.dailyquote.common.model.entity;

import com.mba.dailyquote.common.converter.StatusConverter;
import com.mba.dailyquote.common.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseStatusEntity extends BaseEntity {
    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;
}