package com.copying.blogs.controller.admin;


import com.copying.blogs.constants.Constants;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.util.Base64Util;
import com.copying.blogs.util.IdUtils;
import com.copying.blogs.util.RedisUtil;
import com.copying.blogs.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api
@RestController
public class CaptchaController {
    @Resource
    private RedisUtil redisUtil;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public JsonResult<?> getCode(HttpServletResponse response) throws IOException
    {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisUtil.set(verifyKey,verifyCode,Constants.CAPTCHA_EXPIRATION);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try
        {
            Map<String, String> map = new HashMap<>();
            map.put("uuid", uuid);
            map.put("img", Base64Util.encode(stream.toByteArray()));
            return Result.success(map, ResultCode.SUCCESS);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Result.failAndData(e.getMessage(), ResultCode.SYSTEM_INNER_ERROR);
        }
        finally
        {
            stream.close();
        }
    }
}
