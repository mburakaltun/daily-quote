package com.mba.dailyquote.quoteType.repository;

import com.mba.dailyquote.quoteType.model.entity.QuoteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteTypeRepository extends JpaRepository<QuoteTypeEntity, Long> {
    Optional<QuoteTypeEntity> findByName(String quoteTypeName);
}
