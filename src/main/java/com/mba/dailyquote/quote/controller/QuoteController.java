package com.mba.dailyquote.quote.controller;

import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.quote.model.request.RequestCreateQuote;
import com.mba.dailyquote.quote.model.request.RequestGetAllQuotes;
import com.mba.dailyquote.quote.model.request.RequestUpdateQuote;
import com.mba.dailyquote.quote.model.response.ResponseCreateQuote;
import com.mba.dailyquote.quote.model.response.ResponseGetAllQuotes;
import com.mba.dailyquote.quote.model.response.ResponseGetQuote;
import com.mba.dailyquote.quote.model.response.ResponseUpdateQuote;
import com.mba.dailyquote.quote.service.QuoteService;
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
@RequestMapping("/quotes")
public class QuoteController extends BaseController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateQuote>> createQuote(@Valid @RequestBody RequestCreateQuote requestCreateQuote) throws AppException {
        ResponseCreateQuote response = quoteService.createQuote(requestCreateQuote);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<ResponseUpdateQuote>> updateQuote(@PathVariable("quoteId") Long quoteId, @Valid @RequestBody RequestUpdateQuote requestUpdateQuote) throws AppException {
        requestUpdateQuote.setId(quoteId);
        ResponseUpdateQuote response = quoteService.updateQuote(requestUpdateQuote);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<Void>> deleteQuote(@PathVariable("quoteId") Long quoteId) throws AppException {
        quoteService.deleteQuote(quoteId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<ApiResponse<ResponseGetQuote>> getQuoteById(@PathVariable("quoteId") Long quoteId) throws AppException {
        ResponseGetQuote response = quoteService.getQuoteById(quoteId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetAllQuotes>> getAllQuotes(@Valid @ModelAttribute RequestGetAllQuotes requestGetAllQuotes) {
        ResponseGetAllQuotes response = quoteService.getAllQuotes(requestGetAllQuotes);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }
}