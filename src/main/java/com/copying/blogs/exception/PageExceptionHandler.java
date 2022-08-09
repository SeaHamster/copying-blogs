package com.copying.blogs.exception;

import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/***
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class PageExceptionHandler {

    /***
     * 验证码认证异常
     */
    @ResponseBody
    @ExceptionHandler(CaptchaExpireException.class)
    public Object handleException(CaptchaExpireException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.CODE_AUTH_ERROR);
    }


    /***
     * 请求方式（get/post）异常
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.INTERFACE_METHOD_ERROR);
    }

    /**
     * AccessDeniedException
     */
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public Object handleException(AccessDeniedException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.PERMISSION_NO_ACCESS);
    }

    /***
     * 自定义异常
     */
    @ResponseBody
    @ExceptionHandler(CustomizeException.class)
    public Object handleException(CustomizeException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.ERROR, e.getMsg());
    }

    /***
     * 其他异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(ResultCode.ERROR, "服务器开小差了");
    }

}
