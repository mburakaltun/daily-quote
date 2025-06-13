package com.mba.dailyquote.quoteCategory.model.entity;

import com.mba.dailyquote.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quote_category")
public class QuoteCategoryEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    public String name;
}
