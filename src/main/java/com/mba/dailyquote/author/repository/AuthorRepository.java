package com.mba.dailyquote.author.repository;

import com.mba.dailyquote.author.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByNameIgnoreCaseStartingWith(String namePrefix);

    Optional<AuthorEntity> findByName(String authorName);
}
