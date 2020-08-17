package top.copying.blogs.util.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常具体类型定义
 * @author copying
 * @date  2020-08-15 10:04:51
 */
@Data
public class ResponseCode implements Serializable {
    private static final long serialVersionUID = -2529378242147664639L;
    private static final Map<String, ResponseCode> MAP = new HashMap<>();

    /* *****************************  200 请求成功  **************************** */

    /* -------------  00 系统状态  ------------- */
    /** 请求成功 */
    public static final String SYS_SUCCESSFUL_REQUEST = "SYS_SUCCESSFUL_REQUEST";
    /* -------------  01 通用状态  ------------- */
    /** 不存在该记录 */
    public static final String COMMON_NOT_EXIST = "COMMON_NOT_EXIST";
    /** 已存在该记录 */
    public static final String COMMON_ALREADY_EXIST = "COMMON_ALREADY_EXIST";
    /** 不许重复执行 */
    public static final String COMMON_NOT_ALLOW_REPEAT = "COMMON_NOT_ALLOW_REPEAT";
    /** 不允许的操作 */
    public static final String COMMON_NOT_ALLOW = "COMMON_NOT_ALLOW";
    /** 记录已被修改 */
    public static final String COMMON_CHANGED = "COMMON_CHANGED";
    /** 请求失败 */
    public static final String COMMON_FAIL_REQUEST = "COMMON_FAIL_REQUEST";
    /** 不支持此操作 */
    public static final String COMMON_NOT_SUPPORT = "COMMON_NOT_SUPPORT";
    /* -------------  02 授权状态  ------------- */
    /** 授权失败 */
    public static final String PERMISSION_AUTHORIZE_FAILED = "PERMISSION_AUTHORIZE_FAILED";

    /* -------------  03 角色状态  ------------- */
    /** 无效角色 */
    public static final String ROLE_INVALID = "ROLE_INVALID";
    /** 获取角色失败 */
    public static final String ROLE_NOT_FOUND = "ROLE_NOT_FOUND";
    /* *****************************  400 请求错误  **************************** */

    /* -------------  00 系统状态  ------------- */
    /** 请求body格式错误 */
    public static final String SYS_BODY_FORMAT_ERROR = "SYS_BODY_FORMAT_ERROR";
    /** 上传文件大小超过限制 */
    public static final String SYS_FILE_SIZE_OUT_OF_GAUGE = "SYS_FILE_SIZE_OUT_OF_GAUGE";
    /** 上传文件失败 */
    public static final String UP_LOAD_FILE = "UP_LOAD_FILE";
    /** 不支持的Content */
    public static final String SYS_UNSUPPORTED_CONTENT_TYPE = "SYS_UNSUPPORTED_CONTENT_TYPE";
    /** 丢失请求参数 */
    public static final String COMMON_MISS_PARAMETER = "COMMON_MISS_PARAMETER";
    /** 无效的参数 */
    public static final String COMMON_INVALID_PARAM = "COMMON_INVALID_PARAM";
    /* -------------  01 通用状态  ------------- */
    /** 参数校验错误 */
    public static final String COMMON_VALIDATE_ERROR = "COMMON_VALIDATE_ERROR";
    /** 不在允许值范围内 */
    public static final String COMMON_NOT_IN_ALLOWED_SCOPE = "COMMON_NOT_IN_ALLOWED_SCOPE";
    /* -------------  02 授权状态  ------------- */
    /** 用户名或密码错误 */
    public static final String PERMISSION_INVALID_USERNAME_PASSWORD = "PERMISSION_INVALID_USERNAME_PASSWORD";
    /* *****************************  401 未授权  **************************** */
    /* -------------  02 授权状态  ------------- */
    /** 未授权 */
    public static final String PERMISSION_UNAUTHORIZED = "PERMISSION_UNAUTHORIZED";
    /** IP受限 */
    public static final String PERMISSION_IP_RESTRICTION = "PERMISSION_IP_RESTRICTION";
    /** 权限不足 */
    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
    /** 账号失效 */
    public static final String PERMISSION_INVALID_ACCOUNT = "PERMISSION_INVALID_ACCOUNT";
    /* *****************************  404 路由错误  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 路由不存在 */
    public static final String SYS_NOT_FOUND = "SYS_NOT_FOUND";

    /* *****************************  405 不支持的请求方法  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 不支持的请求方法 */
    public static final String SYS_UNSUPPORTED_METHOD = "SYS_UNSUPPORTED_METHOD";

    /* *****************************  500 系统内部错误  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 系统繁忙 */
    public static final String SYS_INTERNAL_SERVER_ERROR = "SYS_INTERNAL_SERVER_ERROR";
    static {
        /* *****************************  200 请求成功  **************************** */
        MAP.put(SYS_SUCCESSFUL_REQUEST,new ResponseCode(HttpStatus.OK,2000000,"请求成功","Request successful"));
        MAP.put(COMMON_NOT_EXIST,new ResponseCode(HttpStatus.OK,2000100,"记录不存在","Record does not exist"));
        MAP.put(COMMON_ALREADY_EXIST,new ResponseCode(HttpStatus.OK,2000101,"记录已存在","Record already exist"));
        MAP.put(COMMON_NOT_ALLOW_REPEAT,new ResponseCode(HttpStatus.OK,2000101,"不许重复执行","Duplicate execution not allowed"));
        MAP.put(COMMON_NOT_ALLOW,new ResponseCode(HttpStatus.OK,2000103,"不允许的操作","Operation not allowed"));
        MAP.put(COMMON_CHANGED,new ResponseCode(HttpStatus.OK,2000104,"记录已被修改","Record has been modified"));
        MAP.put(COMMON_FAIL_REQUEST,new ResponseCode(HttpStatus.OK,2000105,"请求失败","Request was aborted"));
        MAP.put(COMMON_NOT_SUPPORT,new ResponseCode(HttpStatus.OK,2000106,"不支持此操作","Unsupported operation"));
        MAP.put(PERMISSION_AUTHORIZE_FAILED,new ResponseCode(HttpStatus.OK,2000200,"授权失败","Authorization failed"));
        MAP.put(ROLE_INVALID,new ResponseCode(HttpStatus.OK,2000300,"无效角色","Invalid role"));
        MAP.put(ROLE_NOT_FOUND,new ResponseCode(HttpStatus.OK,2000301,"获取角色失败","Failed to get role"));
        /* *****************************  400 请求错误  **************************** */
        MAP.put(SYS_BODY_FORMAT_ERROR,new ResponseCode(HttpStatus.BAD_REQUEST,4000000,"请求body格式错误","Request body format error"));
        MAP.put(SYS_FILE_SIZE_OUT_OF_GAUGE,new ResponseCode(HttpStatus.BAD_REQUEST,4000010,"上传文件大小超过限制","Upload file size exceeds limit"));
        MAP.put(UP_LOAD_FILE,new ResponseCode(HttpStatus.BAD_REQUEST,4000011,"上传文件失败","Upload file size exceeds limit"));
        MAP.put(SYS_UNSUPPORTED_CONTENT_TYPE,new ResponseCode(HttpStatus.BAD_REQUEST,4000020,"不支持的Content-Type","Unsupported Content-Type"));
        MAP.put(COMMON_MISS_PARAMETER,new ResponseCode(HttpStatus.BAD_REQUEST,4000021,"丢失请求参数","Missing request parameters"));
        MAP.put(COMMON_INVALID_PARAM,new ResponseCode(HttpStatus.BAD_REQUEST,4000022,"无效的参数","Invalid parameter"));
        MAP.put(COMMON_VALIDATE_ERROR,new ResponseCode(HttpStatus.BAD_REQUEST,4000023,"未通过参数校验","Failed parameter verification"));
        MAP.put(COMMON_NOT_IN_ALLOWED_SCOPE,new ResponseCode(HttpStatus.BAD_REQUEST,4000101,"不在允许值范围内","Not in allowed scope"));
        MAP.put(PERMISSION_INVALID_USERNAME_PASSWORD,new ResponseCode(HttpStatus.BAD_REQUEST,4000200,"用户名或密码错误","Wrong user name or password"));
        /* *****************************  401 未授权  **************************** */
        MAP.put(PERMISSION_UNAUTHORIZED,new ResponseCode(HttpStatus.UNAUTHORIZED,4010200,"未授权","Unauthorized"));
        MAP.put(PERMISSION_IP_RESTRICTION,new ResponseCode(HttpStatus.UNAUTHORIZED,4010201,"IP受限","IP restricted"));
        MAP.put(PERMISSION_DENIED,new ResponseCode(HttpStatus.UNAUTHORIZED,4010202,"权限不足","Insufficient authority"));
        MAP.put(PERMISSION_INVALID_ACCOUNT,new ResponseCode(HttpStatus.UNAUTHORIZED,4010203,"无效账户","无效账户"));
        /* *****************************  404 路由错误  **************************** */
        MAP.put(SYS_NOT_FOUND,new ResponseCode(HttpStatus.NOT_FOUND,4040000,"路由不存在","Route does not exist"));
        /* *****************************  405 不支持的请求方法  **************************** */
        MAP.put(SYS_UNSUPPORTED_METHOD,new ResponseCode(HttpStatus.METHOD_NOT_ALLOWED,4050000,"不支持的请求方法","Unsupported request method"));
        /* *****************************  500 系统内部错误  **************************** */
        MAP.put(SYS_INTERNAL_SERVER_ERROR,new ResponseCode(HttpStatus.INTERNAL_SERVER_ERROR,5000000,"系统繁忙","System error"));
    }

    /**
     * http状态码
     */
    private HttpStatus status;
    /**
     * 自定义状态码
     * 自定义状态码一共5位，前3位取http状态码，中间2位表示状态类型，后两位表示具体状态码
     * 状态类型（每个人根据自己需求进行追加）
     *  + 00：系统状态
     *  + 01：通用状态
     *  + 02：授权状态
     *  + 03：角色状态
     */
    private Integer code;
    /**
     * 中文message
     */
    private String chMsg;
    /**
     * 英文message
     */
    private String enMsg;
    /**
     * 字段名
     */
    private String field;

    private ResponseCode(HttpStatus status, Integer code, String chMsg, String enMsg) {
        this.status = status;
        this.code = code;
        this.chMsg = chMsg;
        this.enMsg = enMsg;
    }

    @SuppressWarnings("unused")
    public ResponseCode() {
    }

    public static ResponseCode defaultValidate(String msg){
        ResponseCode instance = instance(msg);
        if(instance == null) {
            instance = instance(COMMON_VALIDATE_ERROR);
            if(msg != null && !msg.isEmpty()) {
                instance.chMsg = msg;
                instance.enMsg = msg;
            }
        }
        return instance;
    }

    public static ResponseCode instance(String key){
        if (key == null){
            return null;
        }
        ResponseCode responseCode = MAP.get(key);
        if(responseCode == null){
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(responseCode),ResponseCode.class);
    }

    public String getChMsg() {
        return ((field == null)? "" : field  + " : ")+ chMsg;
    }

    public String getEnMsg() {
        return ((field == null)? "" : field + " : ") + enMsg;
    }
}
