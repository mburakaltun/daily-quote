package com.mba.dailyquote.book.controller;

import com.mba.dailyquote.book.model.request.RequestCreateBook;
import com.mba.dailyquote.book.model.request.RequestGetAllBooks;
import com.mba.dailyquote.book.model.request.RequestUpdateBook;
import com.mba.dailyquote.book.model.response.ResponseCreateBook;
import com.mba.dailyquote.book.model.response.ResponseGetBook;
import com.mba.dailyquote.book.model.response.ResponseGetBooks;
import com.mba.dailyquote.book.model.response.ResponseUpdateBook;
import com.mba.dailyquote.book.service.BookService;
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
@RequestMapping("/api/v1/books")
public class BookController extends BaseController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateBook>> createBook(@Valid @RequestBody RequestCreateBook requestCreateBook) {
        ResponseCreateBook response = bookService.createBook(requestCreateBook);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<ResponseUpdateBook>> updateBook(@PathVariable("bookId") Long bookId, @Valid @RequestBody RequestUpdateBook requestUpdateBook) throws AppException {
        ResponseUpdateBook response = bookService.updateBook(requestUpdateBook, bookId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable("bookId") Long bookId) throws AppException {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<ResponseGetBook>> getBookById(@PathVariable("bookId") Long bookId) throws AppException {
        ResponseGetBook response = bookService.getBookById(bookId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetBooks>> getAllBooks(@Valid @ModelAttribute RequestGetAllBooks requestGetAllBooks) {
        ResponseGetBooks response = bookService.getAllBooks(requestGetAllBooks);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }
}