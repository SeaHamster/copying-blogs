package top.copying.blogs.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.copying.blogs.util.exception.ResponseCode;

/**
 * 自定义异常
 *
 * @author shuyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizeException extends RuntimeException {
    private static final long serialVersionUID = -7415545560178963092L;
    private ResponseCode responseCode;
    private String chMsg;
    private String enMsg;

    public CustomizeException(String responseCodeKey, String chMsg, String enMsg) {
        this.responseCode = ResponseCode.instance(responseCodeKey);
        this.chMsg = (chMsg == null)? responseCode.getChMsg():chMsg;
        this.enMsg = (enMsg == null)? responseCode.getEnMsg():enMsg;
    }

    public CustomizeException(String responseCodeKey) {
        this(responseCodeKey,null,null);
    }
}
