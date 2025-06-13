package com.mba.dailyquote.quote.model.entity;

import com.mba.dailyquote.author.model.entity.AuthorEntity;
import com.mba.dailyquote.book.model.entity.BookEntity;
import com.mba.dailyquote.common.model.entity.BaseEntity;
import com.mba.dailyquote.quoteCategory.model.entity.QuoteCategoryEntity;
import com.mba.dailyquote.quoteType.model.entity.QuoteTypeEntity;
import com.mba.dailyquote.tag.model.entity.TagEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "quote")
public class QuoteEntity extends BaseEntity {
    private String content;
    private String contentTr;
    private boolean isActive;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_category_id")
    private QuoteCategoryEntity quoteCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_type_id")
    private QuoteTypeEntity quoteType;

    private String source;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "quote_tag",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();
}
