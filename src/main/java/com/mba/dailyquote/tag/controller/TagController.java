package com.mba.dailyquote.tag.controller;

import com.mba.dailyquote.common.controller.BaseController;
import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.tag.model.request.RequestCreateTag;
import com.mba.dailyquote.tag.model.request.RequestGetAllTags;
import com.mba.dailyquote.tag.model.request.RequestUpdateTag;
import com.mba.dailyquote.tag.model.response.ResponseCreateTag;
import com.mba.dailyquote.tag.model.response.ResponseGetTag;
import com.mba.dailyquote.tag.model.response.ResponseGetAllTags;
import com.mba.dailyquote.tag.model.response.ResponseUpdateTag;
import com.mba.dailyquote.tag.service.TagService;
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
@RequestMapping("/tags")
public class TagController extends BaseController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseCreateTag>> createTag(@Valid @RequestBody RequestCreateTag requestCreateTag) {
        ResponseCreateTag response = tagService.createTag(requestCreateTag);
        return new ResponseEntity<>(respond(response), HttpStatus.CREATED);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<ApiResponse<ResponseUpdateTag>> updateTag(@PathVariable("tagId") Long tagId, @Valid @RequestBody RequestUpdateTag requestUpdateTag) throws AppException {
        ResponseUpdateTag response = tagService.updateTag(requestUpdateTag, tagId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<ApiResponse<Void>> deleteTag(@PathVariable("tagId") Long tagId) throws AppException {
        tagService.deleteTag(tagId);
        return new ResponseEntity<>(respond(), HttpStatus.OK);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<ApiResponse<ResponseGetTag>> getTagById(@PathVariable("tagId") Long tagId) throws AppException {
        ResponseGetTag response = tagService.getTagById(tagId);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseGetAllTags>> getAllTags(@Valid @ModelAttribute RequestGetAllTags requestGetAllTags) {
        ResponseGetAllTags response = tagService.getAllTags(requestGetAllTags);
        return new ResponseEntity<>(respond(response), HttpStatus.OK);
    }
}