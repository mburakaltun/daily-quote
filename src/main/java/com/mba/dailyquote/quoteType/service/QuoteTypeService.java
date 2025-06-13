package com.mba.dailyquote.quoteType.service;

import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.quoteType.model.dto.QuoteTypeDto;
import com.mba.dailyquote.quoteType.model.dto.QuoteTypeDto;
import com.mba.dailyquote.quoteType.model.entity.QuoteTypeEntity;
import com.mba.dailyquote.quoteType.model.enums.QuoteTypeErrorCode;
import com.mba.dailyquote.quoteType.model.request.RequestCreateQuoteType;
import com.mba.dailyquote.quoteType.model.request.RequestGetAllQuoteTypes;
import com.mba.dailyquote.quoteType.model.request.RequestUpdateQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseCreateQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseGetQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseGetAllQuoteTypes;
import com.mba.dailyquote.quoteType.model.response.ResponseUpdateQuoteType;
import com.mba.dailyquote.quoteType.repository.QuoteTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuoteTypeService {

    private final QuoteTypeRepository quoteTypeRepository;

    public ResponseCreateQuoteType createQuoteType(RequestCreateQuoteType requestCreateQuoteType) {
        QuoteTypeEntity quoteTypeEntity = new QuoteTypeEntity();
        quoteTypeEntity.setName(requestCreateQuoteType.getName());
        quoteTypeEntity = quoteTypeRepository.save(quoteTypeEntity);
        return ResponseCreateQuoteType.builder()
                .quoteTypeId(quoteTypeEntity.getId())
                .build();
    }

    public ResponseUpdateQuoteType updateQuoteType(RequestUpdateQuoteType requestUpdateQuoteType, Long quoteTypeId) throws AppException {
        QuoteTypeEntity quoteTypeEntity = findQuoteTypeById(quoteTypeId);
        quoteTypeEntity.setName(requestUpdateQuoteType.getName());
        quoteTypeEntity = quoteTypeRepository.save(quoteTypeEntity);
        return ResponseUpdateQuoteType.builder()
                .quoteTypeId(quoteTypeEntity.getId())
                .build();
    }

    public void deleteQuoteType(Long quoteTypeId) throws AppException {
        QuoteTypeEntity quoteTypeEntity = findQuoteTypeById(quoteTypeId);
        quoteTypeRepository.delete(quoteTypeEntity);
    }

    public ResponseGetQuoteType getQuoteTypeById(Long quoteTypeId) throws AppException {
        QuoteTypeEntity quoteTypeEntity = findQuoteTypeById(quoteTypeId);
        QuoteTypeDto quoteTypeDto = mapQuoteTypeEntityToDto(quoteTypeEntity);
        return ResponseGetQuoteType.builder()
                .quoteTypeDto(quoteTypeDto)
                .build();
    }

    public ResponseGetAllQuoteTypes getAllQuoteTypes(RequestGetAllQuoteTypes requestGetAllQuoteTypes) {
        Pageable pageable = PageRequest.of(requestGetAllQuoteTypes.getPage(), requestGetAllQuoteTypes.getSize());
        Page<QuoteTypeEntity> quoteTypePage = quoteTypeRepository.findAll(pageable);

        List<QuoteTypeDto> quoteTypeDtoList = quoteTypePage.getContent().stream()
                .map(this::mapQuoteTypeEntityToDto)
                .collect(Collectors.toList());

        return ResponseGetAllQuoteTypes.builder()
                .quoteTypeDtoList(quoteTypeDtoList)
                .totalElements(quoteTypePage.getTotalElements())
                .totalPages(quoteTypePage.getTotalPages())
                .isLast(quoteTypePage.isLast())
                .build();
    }

    private QuoteTypeDto mapQuoteTypeEntityToDto(QuoteTypeEntity quoteTypeEntity) {
        return QuoteTypeDto.builder()
                .id(quoteTypeEntity.getId())
                .createdDate(quoteTypeEntity.getCreatedDate())
                .updatedDate(quoteTypeEntity.getUpdatedDate())
                .name(quoteTypeEntity.getName())
                .build();
    }

    public QuoteTypeEntity findQuoteTypeById(Long id) throws AppException {
        return quoteTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(QuoteTypeErrorCode.QUOTE_TYPE_NOT_FOUND));
    }

    public QuoteTypeEntity getOrCreateQuoteType(String quoteTypeName) {
        return quoteTypeRepository.findByName(quoteTypeName)
                .orElseGet(() -> {
                    QuoteTypeEntity quoteTypeEntity = new QuoteTypeEntity();
                    quoteTypeEntity.setName(quoteTypeName);
                    return quoteTypeRepository.save(quoteTypeEntity);
                });
    }
}