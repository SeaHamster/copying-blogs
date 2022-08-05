package com.copying.blogs.controller.org.service;

import com.copying.blogs.model.entity.CyBlogsUser;
import org.springframework.stereotype.Service;

/**
 * @author copying
 * @date 2020-08-11
 */
@Service("CyBlogsUserService")
public interface CyBlogsUserService  {

    /**
     * 用户登录
     * @param userName 用户名
     * @param passWord 密码
     * @return 是否登录成功
     */
    Boolean isLogin(String userName,String passWord);

    /**
     * 传入用户信息，创建用户
     * @param cyBlogsUser 用户信息
     * @return 返回用户信息，失败返回null
     */
    CyBlogsUser newUser(CyBlogsUser cyBlogsUser);
}
