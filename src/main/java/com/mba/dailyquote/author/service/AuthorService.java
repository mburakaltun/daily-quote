package com.mba.dailyquote.author.service;

import com.mba.dailyquote.author.model.dto.AuthorDto;
import com.mba.dailyquote.author.model.entity.AuthorEntity;
import com.mba.dailyquote.author.model.enums.AuthorErrorCode;
import com.mba.dailyquote.author.model.request.RequestCreateAuthor;
import com.mba.dailyquote.author.model.request.RequestGetAllAuthors;
import com.mba.dailyquote.author.model.request.RequestUpdateAuthor;
import com.mba.dailyquote.author.model.response.ResponseCreateAuthor;
import com.mba.dailyquote.author.model.response.ResponseGetAllAuthors;
import com.mba.dailyquote.author.model.response.ResponseGetAuthor;
import com.mba.dailyquote.author.model.response.ResponseUpdateAuthor;
import com.mba.dailyquote.author.repository.AuthorRepository;
import com.mba.dailyquote.author.utility.AuthorMapper;
import com.mba.dailyquote.common.exception.AppException;
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

    public ResponseUpdateAuthor updateAuthor(RequestUpdateAuthor requestUpdateAuthor, Long authorId) throws AppException {
        AuthorEntity authorEntity = findAuthorById(authorId);

        authorEntity.setName(requestUpdateAuthor.getName());
        authorEntity = authorRepository.save(authorEntity);
        return ResponseUpdateAuthor.builder()
                .authorId(authorEntity.getId())
                .build();
    }

    public void deleteAuthor(Long authorId) throws AppException {
        AuthorEntity authorEntity = findAuthorById(authorId);
        authorRepository.delete(authorEntity);
    }

    public ResponseGetAuthor getAuthorById(Long authorId) throws AppException {
        AuthorEntity authorEntity = findAuthorById(authorId);
        AuthorDto authorDto = AuthorMapper.toDto(authorEntity);

        return ResponseGetAuthor.builder()
                .authorDto(authorDto)
                .build();
    }

    public ResponseGetAllAuthors getAllAuthors(RequestGetAllAuthors requestGetAllAuthors) {
        Pageable pageable = PageRequest.of(requestGetAllAuthors.getPage(), requestGetAllAuthors.getSize());
        Page<AuthorEntity> authorPage = authorRepository.findAll(pageable);

        List<AuthorDto> authorDtoList = AuthorMapper.toDtoList(authorPage.getContent());

        return ResponseGetAllAuthors.builder()
                .authorDtoList(authorDtoList)
                .totalElements(authorPage.getTotalElements())
                .totalPages(authorPage.getTotalPages())
                .isLast(authorPage.isLast())
                .build();
    }

    public AuthorEntity findAuthorById(Long id) throws AppException {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AppException(AuthorErrorCode.AUTHOR_NOT_FOUND));
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