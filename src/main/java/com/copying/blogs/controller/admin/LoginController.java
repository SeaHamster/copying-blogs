package com.copying.blogs.controller.admin;

import com.copying.blogs.constants.Constants;
import com.copying.blogs.exception.CaptchaExpireException;
import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.copying.blogs.model.entity.SysMenu;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogsUserService;
import com.copying.blogs.service.SysMenuService;
import com.copying.blogs.util.RedisUtil;
import com.copying.blogs.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
public class LoginController {
    @Resource
    private CyBlogsUserService cyBlogsUserService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SysMenuService sysMenuService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult<?> login(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("code") String code, @RequestParam("uuid") String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = (String) redisUtil.get(verifyKey);
        redisUtil.del(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException("验证码不存在");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaExpireException("验证码不匹配");
        }
        Map<String, Object> map = new HashMap<>();
        CyBlogsUser user = cyBlogsUserService.verifyLogin(username, password);
        if (user != null) {
            String token = TokenUtil.sign(user);
            map.put("token", token);
            return Result.success(map, ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.USER_LOGIN_ERROR);
        }

    }

    @RequestMapping(value = {"/getInfo"}, method = RequestMethod.GET)
    public JsonResult<?> getInfo(CyBlogsUserDto userDto) {
        if (userDto != null) {
            userDto.setPassword(null);
            userDto.setUserId(null);
            Map<String, Object> map = new HashMap<>();
            map.put("user", userDto);
            map.put("roles", new String[]{"admin"});
            map.put("permissions", new String[]{"*:*:*"});
            return Result.success(map, ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.DATA_IS_WRONG);
    }

    @RequestMapping(value = "/getRouters", method = RequestMethod.GET)
    public JsonResult<?> getRouters(CyBlogsUserDto userDto) {
        if (userDto == null || userDto.getUserId() == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(userDto.getUserId());
        return Result.success(sysMenuService.buildMenus(menus), ResultCode.SUCCESS);
    }


}
