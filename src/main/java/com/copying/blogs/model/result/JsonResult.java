package com.copying.blogs.model.result;


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

    public JsonResult(Integer code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.data = obj;
    }
}
