package com.copying.blogs.model.result;

/**
 * ResultUtil
 */
@SuppressWarnings("unused")
public class Result {

    //成功，不返回具体数据
    public static <T> JsonResult<T> successNoData(){
        return successNoData(ResultCode.SUCCESS);
    }
    //成功，不返回具体数据
    public static <T> JsonResult<T> successNoData(ResultCode code){
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
    //成功，返回数据
    public static <T> JsonResult<T> success(T t,ResultCode code){
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    //失败，返回失败信息
    public static <T> JsonResult<T> fail(ResultCode code){
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
    //失败，返回失败信息
    public static <T> JsonResult<T> fail(ResultCode code,String msg){
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code.getCode());
        result.setMsg(msg);
        return result;
    }

    //失败，返回失败信息
    public static <T> JsonResult<T> failAndData(T t, ResultCode code){
        JsonResult<T> result = new JsonResult<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

}
