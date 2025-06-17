package com.mba.dailyquote.author.controller;

import com.mba.dailyquote.author.model.request.RequestCreateAuthor;
import com.mba.dailyquote.author.model.request.RequestGetAllAuthors;
import com.mba.dailyquote.author.model.request.RequestUpdateAuthor;
import com.mba.dailyquote.author.model.response.ResponseCreateAuthor;
import com.mba.dailyquote.author.model.response.ResponseGetAllAuthors;
import com.mba.dailyquote.author.model.response.ResponseGetAuthor;
import com.mba.dailyquote.author.model.response.ResponseUpdateAuthor;
import com.mba.dailyquote.author.service.AuthorService;
import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController extends BaseController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateAuthor>> createAuthor(@Valid @RequestBody RequestCreateAuthor requestCreateAuthor) {
        ResponseCreateAuthor response = authorService.createAuthor(requestCreateAuthor);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<ApiResponse<ResponseUpdateAuthor>> updateAuthor(@PathVariable("authorId") Long authorId, @Valid @RequestBody RequestUpdateAuthor requestUpdateAuthor) throws AppException {
        ResponseUpdateAuthor response = authorService.updateAuthor(requestUpdateAuthor, authorId);
        return ResponseEntity.ok(respond(response));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable("authorId") Long authorId) throws AppException {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<ApiResponse<ResponseGetAuthor>> getAuthorById(@PathVariable("authorId") Long authorId) throws AppException {
        ResponseGetAuthor response = authorService.getAuthorById(authorId);
        return ResponseEntity.ok(respond(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetAllAuthors>> getAllAuthors(@Valid @ModelAttribute RequestGetAllAuthors requestGetAllAuthors) {
        ResponseGetAllAuthors response = authorService.getAllAuthors(requestGetAllAuthors);
        return ResponseEntity.ok(respond(response));
    }
}