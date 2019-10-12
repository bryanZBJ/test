package com.example.common.exception;

import com.example.common.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //这就是返回的httpStatus的状态 这个是500
    //@ResponseStatus(HttpStatus.OK) 这个是200
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage());
        return CommonResult.errorResult(CommonResult.FAIL, CommonResult.FAIL_MSG);
    }

    @ExceptionHandler(BizException.class)
    public CommonResult handleBizException(BizException exception) {
        return CommonResult.errorResult(Integer.parseInt(exception.getErrorCode()), exception.getErrorMsg());
    }

}
