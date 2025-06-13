package com.mba.dailyquote.common.controller;

import com.mba.dailyquote.common.model.response.ApiResponse;
import com.mba.dailyquote.common.util.ResponseUtility;

public class BaseController {
    public <T> ApiResponse<T> respond(T data) {
        return ResponseUtility.success(data);
    }

    public ApiResponse<Void> respond() {
        return ResponseUtility.success();
    }
}
