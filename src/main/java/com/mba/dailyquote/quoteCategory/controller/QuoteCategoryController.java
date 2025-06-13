package com.mba.dailyquote.quoteCategory.controller;

import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.quoteCategory.model.request.RequestCreateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.request.RequestGetAllQuoteCategories;
import com.mba.dailyquote.quoteCategory.model.request.RequestUpdateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseCreateQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseGetAllQuoteCategories;
import com.mba.dailyquote.quoteCategory.model.response.ResponseGetQuoteCategory;
import com.mba.dailyquote.quoteCategory.model.response.ResponseUpdateQuoteCategory;
import com.mba.dailyquote.quoteCategory.service.QuoteCategoryService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quote-categories")
public class QuoteCategoryController extends BaseController {

    private final QuoteCategoryService quoteCategoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateQuoteCategory>> createQuoteCategory(@Valid @RequestBody RequestCreateQuoteCategory requestCreateQuoteCategory) {
        ResponseCreateQuoteCategory response = quoteCategoryService.createQuoteCategory(requestCreateQuoteCategory);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{quoteCategoryId}")
    public ResponseEntity<ApiResponse<ResponseUpdateQuoteCategory>> updateQuoteCategory(@PathVariable("quoteCategoryId") Long quoteCategoryId, @Valid @RequestBody RequestUpdateQuoteCategory requestUpdateQuoteCategory) throws AppException {
        ResponseUpdateQuoteCategory response = quoteCategoryService.updateQuoteCategory(requestUpdateQuoteCategory, quoteCategoryId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @DeleteMapping("/{quoteCategoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuoteCategory(@PathVariable("quoteCategoryId") Long quoteCategoryId) throws AppException {
        quoteCategoryService.deleteQuoteCategory(quoteCategoryId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{quoteCategoryId}")
    public ResponseEntity<ApiResponse<ResponseGetQuoteCategory>> getQuoteCategoryById(@PathVariable("quoteCategoryId") Long quoteCategoryId) throws AppException {
        ResponseGetQuoteCategory response = quoteCategoryService.getQuoteCategoryById(quoteCategoryId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetAllQuoteCategories>> getAllQuoteCategories(@Valid @ModelAttribute RequestGetAllQuoteCategories requestGetAllQuoteCategories) {
        ResponseGetAllQuoteCategories response = quoteCategoryService.getAllQuoteCategories(requestGetAllQuoteCategories);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }
}