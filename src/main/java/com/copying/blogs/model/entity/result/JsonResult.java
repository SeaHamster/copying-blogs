package com.copying.blogs.model.entity.result;


import lombok.Data;

import java.io.Serializable;

/**
 * 全局同一返回数据结构
 *
 * @author fzz
 * @date 2021/9/2
 */
@Data
public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = -2073222502861648880L;

    private Integer code;
    private String msg;
    private T data;

    public JsonResult() {
    }

    public JsonResult(T data){
        this(data, ResultCode.SUCCESS);
    }

    public JsonResult(T data, ResultCode resultCode) {
        this(resultCode.getCode(),resultCode.getMsg(), data);
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult<T> ok() {
        return new JsonResult<>();
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(data);
    }
}
