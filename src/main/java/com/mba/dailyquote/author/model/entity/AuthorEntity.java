package com.mba.dailyquote.author.model.entity;

import com.mba.dailyquote.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "author")
public class AuthorEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    public String name;
}
