package com.example.common.util;

import lombok.Data;

@Data
public class CommonResult<T> {

    public static final int SUCCESS = 0;
    public static final String SUCCESS_MSG = "成功";
    public static final int FAIL = -1;
    public static final String FAIL_MSG = "系统繁忙，请稍候再试";

    private int code;

    private String msg;

    private T data;

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult() {

    }

    public static <E> CommonResult<E> success() {
        return new CommonResult(SUCCESS, SUCCESS_MSG, null);
    }

    public static <E> CommonResult<E> success(E data) {
        return new CommonResult(SUCCESS, SUCCESS_MSG, data);
    }

    public static <E> CommonResult<E> fail() {
        return new CommonResult(FAIL, FAIL_MSG, null);
    }

    public static <E> CommonResult<E> fail(E data) {
        return new CommonResult(FAIL, FAIL_MSG, data);
    }

    public static <T> CommonResult<T> errorResult(int errorCode, String errorMsg) {
        return new CommonResult(errorCode, errorMsg, null);
    }

    public static <E> CommonResult<E> fail(String msg) {
        return new CommonResult(FAIL, msg, null);
    }
}
