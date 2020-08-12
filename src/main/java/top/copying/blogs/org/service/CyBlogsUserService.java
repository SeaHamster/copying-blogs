package top.copying.blogs.org.service;

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
}
