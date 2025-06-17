package com.mba.dailyquote.quoteType.controller;

import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.quoteType.model.request.RequestCreateQuoteType;
import com.mba.dailyquote.quoteType.model.request.RequestGetAllQuoteTypes;
import com.mba.dailyquote.quoteType.model.request.RequestUpdateQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseCreateQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseGetQuoteType;
import com.mba.dailyquote.quoteType.model.response.ResponseGetAllQuoteTypes;
import com.mba.dailyquote.quoteType.model.response.ResponseUpdateQuoteType;
import com.mba.dailyquote.quoteType.service.QuoteTypeService;
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
@RequestMapping("/api/v1/quote-types")
public class QuoteTypeController extends BaseController {

    private final QuoteTypeService quoteTypeService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateQuoteType>> createQuoteType(@Valid @RequestBody RequestCreateQuoteType requestCreateQuoteType) {
        ResponseCreateQuoteType response = quoteTypeService.createQuoteType(requestCreateQuoteType);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{quoteTypeId}")
    public ResponseEntity<ApiResponse<ResponseUpdateQuoteType>> updateQuoteType(@PathVariable("quoteTypeId") Long quoteTypeId, @Valid @RequestBody RequestUpdateQuoteType requestUpdateQuoteType) throws AppException {
        ResponseUpdateQuoteType response = quoteTypeService.updateQuoteType(requestUpdateQuoteType, quoteTypeId);
        return ResponseEntity.ok(respond(response));
    }

    @DeleteMapping("/{quoteTypeId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuoteType(@PathVariable("quoteTypeId") Long quoteTypeId) throws AppException {
        quoteTypeService.deleteQuoteType(quoteTypeId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{quoteTypeId}")
    public ResponseEntity<ApiResponse<ResponseGetQuoteType>> getQuoteTypeById(@PathVariable("quoteTypeId") Long quoteTypeId) throws AppException {
        ResponseGetQuoteType response = quoteTypeService.getQuoteTypeById(quoteTypeId);
        return ResponseEntity.ok(respond(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetAllQuoteTypes>> getAllQuoteTypes(@Valid @ModelAttribute RequestGetAllQuoteTypes requestGetAllQuoteTypes) {
        ResponseGetAllQuoteTypes response = quoteTypeService.getAllQuoteTypes(requestGetAllQuoteTypes);
        return ResponseEntity.ok(respond(response));
    }
}