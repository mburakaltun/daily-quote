package com.mba.dailyquote.book.repository;

import com.mba.dailyquote.book.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByTitleStartingWith(String titlePrefix);

    Optional<BookEntity> findByTitle(String bookTitle);
}
