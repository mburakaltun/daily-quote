package com.mba.dailyquote.book.model.entity;

import com.mba.dailyquote.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    public String title;
}
