package com.mba.dailyquote.author.service;

import com.mba.dailyquote.author.model.dto.AuthorDTO;
import com.mba.dailyquote.author.model.entity.AuthorEntity;
import com.mba.dailyquote.author.model.request.RequestCreateAuthor;
import com.mba.dailyquote.author.model.request.RequestGetAllAuthors;
import com.mba.dailyquote.author.model.request.RequestUpdateAuthor;
import com.mba.dailyquote.author.model.response.ResponseCreateAuthor;
import com.mba.dailyquote.author.model.response.ResponseGetAllAuthors;
import com.mba.dailyquote.author.model.response.ResponseGetAuthor;
import com.mba.dailyquote.author.model.response.ResponseUpdateAuthor;
import com.mba.dailyquote.author.repository.AuthorRepository;
import com.mba.dailyquote.author.utility.AuthorMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public ResponseCreateAuthor createAuthor(RequestCreateAuthor requestCreateAuthor) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(requestCreateAuthor.getName());

        authorEntity = authorRepository.save(authorEntity);
        return ResponseCreateAuthor.builder()
                .authorId(authorEntity.getId())
                .build();
    }

    public ResponseUpdateAuthor updateAuthor(RequestUpdateAuthor requestUpdateAuthor, Long authorId) {
        AuthorEntity authorEntity = findAuthorById(authorId);

        authorEntity.setName(requestUpdateAuthor.getName());
        authorEntity = authorRepository.save(authorEntity);
        return ResponseUpdateAuthor.builder()
                .authorId(authorEntity.getId())
                .build();
    }

    public void deleteAuthor(Long authorId) {
        AuthorEntity authorEntity = findAuthorById(authorId);
        authorRepository.delete(authorEntity);
    }

    public ResponseGetAuthor getAuthorById(Long authorId) {
        AuthorEntity authorEntity = findAuthorById(authorId);
        AuthorDTO authorDTO = AuthorMapper.toDto(authorEntity);

        return ResponseGetAuthor.builder()
                .authorDTO(authorDTO)
                .build();
    }

    public ResponseGetAllAuthors getAllAuthors(RequestGetAllAuthors requestGetAllAuthors) {
        Pageable pageable = PageRequest.of(requestGetAllAuthors.getPage(), requestGetAllAuthors.getSize());
        Page<AuthorEntity> authorPage = authorRepository.findAll(pageable);

        List<AuthorDTO> authorDTOList = AuthorMapper.toDtoList(authorPage.getContent());

        return ResponseGetAllAuthors.builder()
                .authorDTOList(authorDTOList)
                .totalElements(authorPage.getTotalElements())
                .totalPages(authorPage.getTotalPages())
                .isLast(authorPage.isLast())
                .build();
    }

    public AuthorEntity findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author with id %d not found", id)));
    }

    public AuthorEntity getOrCreateAuthor(String authorName) {
        return authorRepository.findByName(authorName)
                .orElseGet(() -> {
                    AuthorEntity authorEntity = new AuthorEntity();
                    authorEntity.setName(authorName);
                    return authorRepository.save(authorEntity);
                });
    }
}