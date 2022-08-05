package com.copying.blogs.exception;

import com.copying.blogs.model.entity.result.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 *
 * @author shuyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizeException extends RuntimeException {
    private static final long serialVersionUID = -7415545560178963092L;
    private HttpStatus status;
    private ResultCode resultCode;
    private Integer code;
    private String msg;

    public CustomizeException() {
        super();
    }

    public CustomizeException(ResultCode resultCode){
        this(resultCode.getMsg(),HttpStatus.INTERNAL_SERVER_ERROR,resultCode);
    }

    public CustomizeException(String msg) {
        this(msg,HttpStatus.INTERNAL_SERVER_ERROR,ResultCode.ERROR);
    }

    public CustomizeException(ResultCode resultCode,String msg){
        this(msg,HttpStatus.INTERNAL_SERVER_ERROR,resultCode);
    }

    protected CustomizeException(String msg,HttpStatus status,ResultCode resultCode) {
        this.msg = msg;
        this.status = status;
        this.resultCode = resultCode;
    }
}
