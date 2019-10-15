package com.example.common.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应主体
 *
 * @author Donald
 * @date 2018年7月23日
 */
@Data
public class R<T> implements Serializable {
    public static final int SUCCESS = 0;
    public static final String SUCCESS_MSG = "成功";

    public static final int FAIL = -1;
    public static final String FAIL_MSG = "系统繁忙，请稍候再试";

    public static final int INVALID_PARAMETER = -2;
    public static final String INVALID_PARAMETER_MSG = "请求参数无效";

    public static final int INCORRECT_SIGNATURE = -3;
    public static final String INCORRECT_SIGNATURE_MSG = "无效签名";

    public static final int TOKEN_INVALID = -4;
    public static final String TOKEN_INVALID_MSG = "无效的token";

    public static final int TOKEN_EXPIRED = -5;
    public static final String TOKEN_EXPIRED_MSG = "token过期";

    public static final int DATABASE_ERROR = -6;
    public static final String DATABASE_ERROR_MSG = "数据库操作出错，请重试";

    private static final long serialVersionUID = 1L;
    private int code = SUCCESS;
    private String msg = "success";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public R() {
        super();
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return
     */
    public static <E> R<E> success() {
        return new R(SUCCESS, "success", null);
    }

    /**
     * 返回成功消息
     *
     * @param data
     * @return
     */
    public static <E> R<E> success(E data) {
        return new R(SUCCESS, "success", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg
     * @return
     */
    public static <E> R<E> success(String msg) {
        return new R(SUCCESS, msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg
     * @param data
     * @return
     */
    public static <E> R<E> success(String msg, E data) {
        return new R(SUCCESS, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @return
     */
    public static <E> R<E> fail() {
        return new R(FAIL, FAIL_MSG, null);
    }

    /**
     * 返回失败消息
     *
     * @return
     */
    public static <E> R<E> fail(E data) {
        return new R(FAIL, FAIL_MSG, data);
    }

    /**
     * 返回失败消息
     *
     * @param msg
     * @return
     */
    public static <E> R<E> fail(String msg) {
        return new R(FAIL, msg, null);
    }

    /**
     * 返回失败消息
     *
     * @param msg
     * @return
     */
    public static <E> R<E> fail(String msg, E data) {
        return new R(FAIL, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @param code
     * @param msg
     * @return
     */
    public static <E> R<E> fail(int code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 返回失败消息
     *
     * @param code
     * @param msg
     * @param data
     * @param <E>
     * @return
     */
    public static <E> R<E> fail(int code, String msg, E data) {
        return new R(code, msg, data);
    }


    /**
     * 返回失败消息
     *
     * @param throwable
     * @return
     */
    public static <E> R<E> fail(Throwable throwable) {
        return fail(throwable != null ? throwable.getMessage() : FAIL_MSG);
    }

    /**
     * 判断是否成功
     *
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return this.code == SUCCESS;
    }

}
