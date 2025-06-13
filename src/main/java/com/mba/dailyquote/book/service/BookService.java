package com.mba.dailyquote.book.service;

import com.mba.dailyquote.book.model.dto.BookDto;
import com.mba.dailyquote.book.model.entity.BookEntity;
import com.mba.dailyquote.book.model.enums.BookErrorCode;
import com.mba.dailyquote.book.model.request.RequestCreateBook;
import com.mba.dailyquote.book.model.request.RequestGetAllBooks;
import com.mba.dailyquote.book.model.request.RequestUpdateBook;
import com.mba.dailyquote.book.model.response.ResponseCreateBook;
import com.mba.dailyquote.book.model.response.ResponseGetBook;
import com.mba.dailyquote.book.model.response.ResponseGetBooks;
import com.mba.dailyquote.book.model.response.ResponseUpdateBook;
import com.mba.dailyquote.book.repository.BookRepository;
import com.mba.dailyquote.book.utility.BookMapper;
import com.mba.dailyquote.common.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public ResponseCreateBook createBook(RequestCreateBook requestCreateBook) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(requestCreateBook.getTitle());
        bookEntity = bookRepository.save(bookEntity);
        return ResponseCreateBook.builder()
                .bookId(bookEntity.getId())
                .build();
    }

    public ResponseUpdateBook updateBook(RequestUpdateBook requestUpdateBook, Long bookId) throws AppException {
        BookEntity bookEntity = findBookById(bookId);
        bookEntity.setTitle(requestUpdateBook.getTitle());
        bookEntity = bookRepository.save(bookEntity);
        return ResponseUpdateBook.builder()
                .bookId(bookEntity.getId())
                .build();
    }

    public void deleteBook(Long bookId) throws AppException {
        BookEntity bookEntity = findBookById(bookId);
        bookRepository.delete(bookEntity);
    }

    public ResponseGetBook getBookById(Long bookId) throws AppException {
        BookEntity bookEntity = findBookById(bookId);
        BookDto bookDto = BookMapper.toDto(bookEntity);
        return ResponseGetBook.builder()
                .bookDto(bookDto)
                .build();
    }

    public ResponseGetBooks getAllBooks(RequestGetAllBooks requestGetAllBooks) {
        Pageable pageable = PageRequest.of(requestGetAllBooks.getPage(), requestGetAllBooks.getSize());
        Page<BookEntity> bookPage = bookRepository.findAll(pageable);

        List<BookDto> bookDtoList = BookMapper.toDtoList(bookPage.getContent());

        return ResponseGetBooks.builder()
                .bookDtoList(bookDtoList)
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .isLast(bookPage.isLast())
                .build();
    }

    public BookEntity findBookById(Long id) throws AppException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new AppException(BookErrorCode.BOOK_NOT_FOUND));
    }

    public BookEntity getOrCreateBook(String bookTitle) {
        return bookRepository.findByTitle(bookTitle)
                .orElseGet(() -> {
                    BookEntity bookEntity = new BookEntity();
                    bookEntity.setTitle(bookTitle);
                    return bookRepository.save(bookEntity);
                });
    }
}