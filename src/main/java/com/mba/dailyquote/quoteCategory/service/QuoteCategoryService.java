package com.mba.dailyquote.quoteCategory.service;

import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.quoteCategory.model.dto.QuoteCategoryDto;
import com.mba.dailyquote.quoteCategory.model.entity.QuoteCategoryEntity;
import com.mba.dailyquote.quoteCategory.model.enums.QuoteCategoryErrorCode;
import com.mba.dailyquote.quoteCategory.model.request.RequestCreateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.request.RequestGetAllQuoteCategories;
import com.mba.dailyquote.quoteCategory.model.request.RequestUpdateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseCreateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseGetAllQuoteCategories;
import com.mba.dailyquote.quoteCategory.model.response.ResponseGetQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseUpdateQuoteCategory;
import com.mba.dailyquote.quoteCategory.repository.QuoteCategoryRepository;
import com.mba.dailyquote.quoteCategory.utility.QuoteCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuoteCategoryService {

    private final QuoteCategoryRepository quoteCategoryRepository;

    public ResponseCreateQuoteCategory createQuoteCategory(RequestCreateQuoteCategory requestCreateQuoteCategory) {
        QuoteCategoryEntity quoteCategoryEntity = new QuoteCategoryEntity();
        quoteCategoryEntity.setName(requestCreateQuoteCategory.getName());
        quoteCategoryEntity = quoteCategoryRepository.save(quoteCategoryEntity);
        return ResponseCreateQuoteCategory.builder()
                .quoteCategoryId(quoteCategoryEntity.getId())
                .build();
    }

    public ResponseUpdateQuoteCategory updateQuoteCategory(RequestUpdateQuoteCategory requestUpdateQuoteCategory, Long quoteCategoryId) throws AppException {
        QuoteCategoryEntity quoteCategoryEntity = findQuoteCategoryById(quoteCategoryId);
        quoteCategoryEntity.setName(requestUpdateQuoteCategory.getName());
        quoteCategoryEntity = quoteCategoryRepository.save(quoteCategoryEntity);
        return ResponseUpdateQuoteCategory.builder()
                .quoteCategoryId(quoteCategoryEntity.getId())
                .build();
    }

    public void deleteQuoteCategory(Long quoteCategoryId) throws AppException {
        QuoteCategoryEntity quoteCategoryEntity = findQuoteCategoryById(quoteCategoryId);
        quoteCategoryRepository.delete(quoteCategoryEntity);
    }

    public ResponseGetQuoteCategory getQuoteCategoryById(Long quoteCategoryId) throws AppException {
        QuoteCategoryEntity quoteCategoryEntity = findQuoteCategoryById(quoteCategoryId);
        QuoteCategoryDto quoteCategoryDto = QuoteCategoryMapper.toDto(quoteCategoryEntity);
        return ResponseGetQuoteCategory.builder()
                .quoteCategoryDto(quoteCategoryDto)
                .build();
    }

    public ResponseGetAllQuoteCategories getAllQuoteCategories(RequestGetAllQuoteCategories requestGetAllQuoteCategories) {
        Pageable pageable = PageRequest.of(requestGetAllQuoteCategories.getPage(), requestGetAllQuoteCategories.getSize());
        Page<QuoteCategoryEntity> quoteCategoryPage = quoteCategoryRepository.findAll(pageable);

        List<QuoteCategoryDto> quoteCategoryDtoList = QuoteCategoryMapper.toDtoList(quoteCategoryPage.getContent());

        return ResponseGetAllQuoteCategories.builder()
                .quoteCategoryDtoList(quoteCategoryDtoList)
                .totalElements(quoteCategoryPage.getTotalElements())
                .totalPages(quoteCategoryPage.getTotalPages())
                .isLast(quoteCategoryPage.isLast())
                .build();
    }

    public QuoteCategoryEntity findQuoteCategoryById(Long id) throws AppException {
        return quoteCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(QuoteCategoryErrorCode.QUOTE_CATEGORY_NOT_FOUND));
    }

    public QuoteCategoryEntity getOrCreateQuoteCategory(String quoteCategoryName) {
        return quoteCategoryRepository.findByName(quoteCategoryName)
                .orElseGet(() -> {
                    QuoteCategoryEntity quoteCategoryEntity = new QuoteCategoryEntity();
                    quoteCategoryEntity.setName(quoteCategoryName);
                    return quoteCategoryRepository.save(quoteCategoryEntity);
                });
    }
}