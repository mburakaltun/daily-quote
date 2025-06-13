package com.mba.dailyquote.quoteCategory.repository;

import com.mba.dailyquote.quoteCategory.model.entity.QuoteCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteCategoryRepository extends JpaRepository<QuoteCategoryEntity, Long> {
    List<QuoteCategoryEntity> findByNameStartingWith(String namePrefix);

    Optional<QuoteCategoryEntity> findByName(String quoteCategoryName);
}
