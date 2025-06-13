package com.mba.dailyquote.tag.model.entity;

import com.mba.dailyquote.common.model.entity.BaseEntity;
import com.mba.dailyquote.quote.model.entity.QuoteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class TagEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<QuoteEntity> quotes = new HashSet<>();
}
