package com.mba.dailyquote.common.service;

import com.mba.dailyquote.common.model.entity.RequestLogEntity;
import com.mba.dailyquote.common.model.request.CreateRequestLogRequest;
import com.mba.dailyquote.common.model.response.CreateRequestLogResponse;
import com.mba.dailyquote.common.repository.RequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class RequestLogService {
    private final RequestLogRepository requestLogRepository;

    @Async
    public CompletableFuture<CreateRequestLogResponse> createRequestLog(CreateRequestLogRequest createRequestLogRequest) {
        RequestLogEntity requestLogEntity = new RequestLogEntity();
        requestLogEntity.setRequestUrl(createRequestLogRequest.getRequestUrl());
        requestLogEntity.setRequestMethod(createRequestLogRequest.getRequestMethod());
        requestLogEntity.setRequestIp(createRequestLogRequest.getRequestIp());
        requestLogEntity.setRequestPayload(createRequestLogRequest.getRequestPayload());
        requestLogEntity.setResponsePayload(createRequestLogRequest.getResponsePayload());
        requestLogEntity.setResponseStatus(createRequestLogRequest.getResponseStatus());
        requestLogEntity.setResponseTime(createRequestLogRequest.getResponseTime());
        requestLogEntity = requestLogRepository.save(requestLogEntity);
        CreateRequestLogResponse response = CreateRequestLogResponse.builder()
                .build();
        return CompletableFuture.completedFuture(response);
    }
}