package top.copying.blogs.util;

import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;
import top.copying.blogs.util.exception.ResponseCode;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author copying
 * @date 2020-08-17 08:38:46
 * 响应消息主体，所有返回数据必须以此类型返回，以确保标准
 */
@Data
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -3012231789196515831L;
    private T data;
    private Integer code;
    private String message;

    public ApiResult(){
        this(null, ResponseCode.instance(ResponseCode.SYS_SUCCESSFUL_REQUEST));
    }
    public ApiResult(T data){
        this(data,ResponseCode.instance(ResponseCode.SYS_SUCCESSFUL_REQUEST));
    }

    public ApiResult(ResponseCode responseCode){
        this(null,responseCode);
    }

    public ApiResult(ResponseCode responseCode,String chMsg,String enMsg){
        this(null,responseCode,chMsg,enMsg);
    }

    public ApiResult(T data,ResponseCode responseCode){
        this(data,responseCode,null,null);
    }
    public ApiResult(T data,ResponseCode responseCode,String chMsg,String enMsg){
        Locale locale= LocaleContextHolder.getLocale();
        if(Locale.CHINESE.getLanguage().equals(locale.getLanguage())){
            this.message=(chMsg==null)?responseCode.getChMsg():chMsg;
        }else {
            this.message=(enMsg ==null )?responseCode.getEnMsg():enMsg;
        }
        this.data=data;
        this.code=responseCode.getCode();
    }
    public static <T> ApiResult<T> ok(){
        return new ApiResult<>();
    }
    public static <T> ApiResult<T> ok(T data){
        return new ApiResult<>(data);
    }
}
