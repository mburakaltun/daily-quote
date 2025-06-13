package com.mba.dailyquote.quote.repository;

import com.mba.dailyquote.quote.model.entity.QuoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    @Query(value = "SELECT q FROM QuoteEntity q JOIN FETCH q.author LEFT JOIN FETCH q.book JOIN FETCH q.quoteCategory JOIN FETCH q.quoteType LEFT JOIN FETCH q.tags",
            countQuery = "SELECT COUNT(q) FROM QuoteEntity q")
    Page<QuoteEntity> findAllWithDetailsPaged(Pageable pageable);
}