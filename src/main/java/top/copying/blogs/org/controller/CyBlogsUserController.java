package top.copying.blogs.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.copying.blogs.org.service.CyBlogsUserService;

import javax.annotation.Resource;


/**
 * @author copying
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/user")
@Api(value = "user management", tags = {"用户管理接口"})
public class CyBlogsUserController {
    @Resource
    private CyBlogsUserService cyBlogsUserService;

    @ApiOperation(notes = "用户登录", value = "用户登录", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",type = "String",required = true),
            @ApiImplicitParam(name = "passWord",value = "密码",type = "String",required = true)
    })
    @GetMapping("/login")
    public Boolean isLogin(String userName, String passWord){
        return cyBlogsUserService.isLogin(userName,passWord);
    }

}
