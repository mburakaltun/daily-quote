package com.mba.dailyquote.tag.service;

import com.mba.dailyquote.common.exception.AppException;
import com.mba.dailyquote.tag.model.dto.TagDto;
import com.mba.dailyquote.tag.model.entity.TagEntity;
import com.mba.dailyquote.tag.model.enums.TagErrorCode;
import com.mba.dailyquote.tag.model.request.RequestCreateTag;
import com.mba.dailyquote.tag.model.request.RequestGetAllTags;
import com.mba.dailyquote.tag.model.request.RequestUpdateTag;
import com.mba.dailyquote.tag.model.response.ResponseCreateTag;
import com.mba.dailyquote.tag.model.response.ResponseGetTag;
import com.mba.dailyquote.tag.model.response.ResponseGetAllTags;
import com.mba.dailyquote.tag.model.response.ResponseUpdateTag;
import com.mba.dailyquote.tag.repository.TagRepository;
import com.mba.dailyquote.tag.utility.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public ResponseCreateTag createTag(RequestCreateTag requestCreateTag) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(requestCreateTag.getName());

        tagEntity = tagRepository.save(tagEntity);
        return ResponseCreateTag.builder()
                .tagId(tagEntity.getId())
                .build();
    }

    public ResponseUpdateTag updateTag(RequestUpdateTag requestUpdateTag, Long tagId) throws AppException {
        TagEntity tagEntity = findTagById(tagId);
        tagEntity.setName(requestUpdateTag.getName());
        tagEntity = tagRepository.save(tagEntity);
        return ResponseUpdateTag.builder()
                .tagId(tagEntity.getId())
                .build();
    }

    public void deleteTag(Long tagId) throws AppException {
        TagEntity tagEntity = findTagById(tagId);
        tagRepository.delete(tagEntity);
    }

    public ResponseGetTag getTagById(Long tagId) throws AppException {
        TagEntity tagEntity = findTagById(tagId);
        TagDto tagDto = TagMapper.toDto(tagEntity);

        return ResponseGetTag.builder()
                .tagDto(tagDto)
                .build();
    }

    public ResponseGetAllTags getAllTags(RequestGetAllTags requestGetAllTags) {
        Pageable pageable = PageRequest.of(requestGetAllTags.getPage(), requestGetAllTags.getSize());
        Page<TagEntity> tagPage = tagRepository.findAll(pageable);

        List<TagDto> tagDtoList = TagMapper.toDtoList(tagPage.getContent());

        return ResponseGetAllTags.builder()
                .tagDtoList(tagDtoList)
                .totalElements(tagPage.getTotalElements())
                .totalPages(tagPage.getTotalPages())
                .isLast(tagPage.isLast())
                .build();
    }

    public TagEntity getOrCreateTag(String name) {
        return tagRepository.findByName(name)
                .orElseGet(() -> {
                    TagEntity tagEntity = new TagEntity();
                    tagEntity.setName(name);
                    return tagRepository.save(tagEntity);
                });
    }

    public TagEntity findTagById(Long id) throws AppException {
        return tagRepository.findById(id)
                .orElseThrow(() -> new AppException(TagErrorCode.TAG_NOT_FOUND));
    }
}