package com.mba.dailyquote.quote.service;

import com.mba.dailyquote.author.service.AuthorService;
import com.mba.dailyquote.book.service.BookService;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.util.StringUtility;
import com.mba.dailyquote.quote.model.dto.QuoteDto;
import com.mba.dailyquote.quote.model.entity.QuoteEntity;
import com.mba.dailyquote.quote.model.enums.QuoteErrorCode;
import com.mba.dailyquote.quote.model.request.RequestCreateQuote;
import com.mba.dailyquote.quote.model.request.RequestGetAllQuotes;
import com.mba.dailyquote.quote.model.request.RequestUpdateQuote;
import com.mba.dailyquote.quote.model.response.ResponseCreateQuote;
import com.mba.dailyquote.quote.model.response.ResponseGetAllQuotes;
import com.mba.dailyquote.quote.model.response.ResponseGetQuote;
import com.mba.dailyquote.quote.model.response.ResponseUpdateQuote;
import com.mba.dailyquote.quote.repository.QuoteRepository;
import com.mba.dailyquote.quoteCategory.service.QuoteCategoryService;
import com.mba.dailyquote.quoteType.service.QuoteTypeService;
import com.mba.dailyquote.tag.model.entity.TagEntity;
import com.mba.dailyquote.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final TagService tagService;
    private final AuthorService authorService;
    private final QuoteCategoryService quoteCategoryService;
    private final QuoteTypeService quoteTypeService;
    private final BookService bookService;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseCreateQuote createQuote(RequestCreateQuote requestCreateQuote) throws AppException {
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setContent(requestCreateQuote.getContent());
        quoteEntity.setContentTr(requestCreateQuote.getContentTr());
        quoteEntity.setNote(requestCreateQuote.getNote());
        quoteEntity.setSource(requestCreateQuote.getSource());
        quoteEntity.setActive(requestCreateQuote.isActive());

        if (requestCreateQuote.getAuthorId() != null) {
            quoteEntity.setAuthor(authorService.findAuthorById(requestCreateQuote.getAuthorId()));
        } else if (StringUtility.isNotBlank(requestCreateQuote.getAuthorName())) {
            quoteEntity.setAuthor(authorService.getOrCreateAuthor(requestCreateQuote.getAuthorName()));
        }

        if (requestCreateQuote.getQuoteCategoryId() != null) {
            quoteEntity.setQuoteCategory(quoteCategoryService.findQuoteCategoryById(requestCreateQuote.getQuoteCategoryId()));
        } else if (StringUtility.isNotBlank(requestCreateQuote.getQuoteCategoryName())) {
            quoteEntity.setQuoteCategory(quoteCategoryService.getOrCreateQuoteCategory(requestCreateQuote.getQuoteCategoryName()));
        }

        if (requestCreateQuote.getQuoteTypeId() != null) {
            quoteEntity.setQuoteType(quoteTypeService.findQuoteTypeById(requestCreateQuote.getQuoteTypeId()));
        } else if (StringUtility.isNotBlank(requestCreateQuote.getQuoteTypeName())) {
            quoteEntity.setQuoteType(quoteTypeService.getOrCreateQuoteType(requestCreateQuote.getQuoteTypeName()));
        }

        if (requestCreateQuote.getBookId() != null) {
            quoteEntity.setBook(bookService.findBookById(requestCreateQuote.getBookId()));
        } else if (StringUtility.isNotBlank(requestCreateQuote.getBookTitle())) {
            quoteEntity.setBook(bookService.getOrCreateBook(requestCreateQuote.getBookTitle()));
        }

        String tagNameList = requestCreateQuote.getTagList();
        Set<TagEntity> tagEntitySet = StringUtility.isBlank(tagNameList) ? Set.of() :
                Arrays.stream(tagNameList.split(","))
                        .map(String::trim)
                        .map(tagService::getOrCreateTag)
                        .collect(Collectors.toSet());

        quoteEntity.setTags(tagEntitySet);
        quoteEntity = quoteRepository.save(quoteEntity);
        return ResponseCreateQuote.builder()
                .quoteId(quoteEntity.getId())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseUpdateQuote updateQuote(RequestUpdateQuote requestUpdateQuote, Long quoteId) throws AppException {
        QuoteEntity quoteEntity = findQuoteById(quoteId);

        quoteEntity.setContent(requestUpdateQuote.getContent());
        quoteEntity.setContentTr(requestUpdateQuote.getContentTr());
        quoteEntity.setNote(requestUpdateQuote.getNote());
        quoteEntity.setSource(requestUpdateQuote.getSource());
        quoteEntity.setActive(requestUpdateQuote.isActive());

        if (requestUpdateQuote.getAuthorId() != null) {
            quoteEntity.setAuthor(authorService.findAuthorById(requestUpdateQuote.getAuthorId()));
        } else if (StringUtility.isNotBlank(requestUpdateQuote.getAuthorName())) {
            quoteEntity.setAuthor(authorService.getOrCreateAuthor(requestUpdateQuote.getAuthorName()));
        }

        if (requestUpdateQuote.getQuoteCategoryId() != null) {
            quoteEntity.setQuoteCategory(quoteCategoryService.findQuoteCategoryById(requestUpdateQuote.getQuoteCategoryId()));
        } else if (StringUtility.isNotBlank(requestUpdateQuote.getQuoteCategoryName())) {
            quoteEntity.setQuoteCategory(quoteCategoryService.getOrCreateQuoteCategory(requestUpdateQuote.getQuoteCategoryName()));
        }

        if (requestUpdateQuote.getQuoteTypeId() != null) {
            quoteEntity.setQuoteType(quoteTypeService.findQuoteTypeById(requestUpdateQuote.getQuoteTypeId()));
        } else if (StringUtility.isNotBlank(requestUpdateQuote.getQuoteTypeName())) {
            quoteEntity.setQuoteType(quoteTypeService.getOrCreateQuoteType(requestUpdateQuote.getQuoteTypeName()));
        }

        if (requestUpdateQuote.getBookId() != null) {
            quoteEntity.setBook(bookService.findBookById(requestUpdateQuote.getBookId()));
        } else if (StringUtility.isNotBlank(requestUpdateQuote.getBookTitle())) {
            quoteEntity.setBook(bookService.getOrCreateBook(requestUpdateQuote.getBookTitle()));
        } else if (StringUtility.isBlank(requestUpdateQuote.getBookTitle())) {
            quoteEntity.setBook(null);
        }

        String tagNameList = requestUpdateQuote.getTagList();
        Set<TagEntity> tagEntitySet = StringUtility.isBlank(tagNameList) ? Set.of() :
                Arrays.stream(tagNameList.split(StringUtility.COMMA))
                        .map(String::trim)
                        .map(tagService::getOrCreateTag)
                        .collect(Collectors.toSet());

        quoteEntity.setTags(tagEntitySet);

        quoteEntity = quoteRepository.save(quoteEntity);
        return ResponseUpdateQuote.builder()
                .quoteId(quoteEntity.getId())
                .build();
    }

    public void deleteQuote(Long quoteId) throws AppException {
        QuoteEntity quoteEntity = findQuoteById(quoteId);
        quoteRepository.delete(quoteEntity);
    }

    public ResponseGetQuote getQuoteById(Long quoteId) throws AppException {
        QuoteEntity quoteEntity = findQuoteById(quoteId);
        QuoteDto quoteDto = mapQuoteEntityToDto(quoteEntity);

        return ResponseGetQuote.builder()
                .quoteDto(quoteDto)
                .build();
    }

    public ResponseGetAllQuotes getAllQuotes(RequestGetAllQuotes requestGetAllQuotes) {
        Pageable pageable = PageRequest.of(requestGetAllQuotes.getPage(), requestGetAllQuotes.getSize());
        Page<QuoteEntity> quotePage = quoteRepository.findAllWithDetailsPaged(pageable);

        List<QuoteDto> quoteDtoList = quotePage.getContent().stream()
                .map(this::mapQuoteEntityToDto)
                .collect(Collectors.toList());

        return ResponseGetAllQuotes.builder()
                .quoteDtoList(quoteDtoList)
                .totalElements(quotePage.getTotalElements())
                .totalPages(quotePage.getTotalPages())
                .isLast(quotePage.isLast())
                .build();
    }

    private QuoteDto mapQuoteEntityToDto(QuoteEntity quoteEntity) {
        return QuoteDto.builder()
                .id(quoteEntity.getId())
                .createdDate(quoteEntity.getCreatedDate())
                .updatedDate(quoteEntity.getUpdatedDate())
                .content(quoteEntity.getContent())
                .contentTr(quoteEntity.getContentTr())
                .note(quoteEntity.getNote())
                .source(quoteEntity.getSource())
                .isActive(quoteEntity.isActive())
                .quoteCategoryName(quoteEntity.getQuoteCategory().getName())
                .quoteTypeName(quoteEntity.getQuoteType().getName())
                .authorName(quoteEntity.getAuthor().getName())
                .bookTitle(quoteEntity.getBook() != null ? quoteEntity.getBook().getTitle() : StringUtility.EMPTY)
                .tagList(quoteEntity.getTags().stream().map(TagEntity::getName).collect(Collectors.toList()))
                .build();
    }

    public QuoteEntity findQuoteById(Long id) throws AppException {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new AppException(QuoteErrorCode.QUOTE_NOT_FOUND));
    }
}