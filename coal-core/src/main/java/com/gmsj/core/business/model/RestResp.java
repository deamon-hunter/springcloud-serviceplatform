/*
 * Copyright (c) 2017 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.core.business.model;


import com.gmsj.core.business.exception.CommonErrorCode;
import com.gmsj.core.business.exception.ErrorCode;
import lombok.Data;

@Data
public class RestResp<T> {

    /**
     * 成功代码
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 失败代码(大于0，默认为1)
     */
    public static final int CODE_FAILURE = 1;

    private Integer code;
    private T data;
    private String msg = "ok";
    private Long timestamp;

    public RestResp() {
    }

    public RestResp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static RestResp ok(String msg) {
        return new RestResp(CODE_SUCCESS, msg, null);
    }

    public static <T> RestResp ok(T data) {
        return new RestResp(CODE_SUCCESS, "ok", data);
    }

    public static <T> RestResp error(Integer code, String msg) {
        return new RestResp(code, msg, null);
    }

    public static RestResp error(ErrorCode code) {
        return error(code.getCode(), code.getErrorMsg());
    }

    public static RestResp ok() {
        return ok("success");
    }

    public static RestResp error() {
        return error(CommonErrorCode.INNER_ERROR);
    }
}
