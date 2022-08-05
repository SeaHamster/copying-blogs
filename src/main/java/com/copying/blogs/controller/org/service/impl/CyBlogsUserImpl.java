package com.copying.blogs.controller.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.copying.blogs.model.entity.CyBlogsUser;
import org.springframework.stereotype.Component;
import com.copying.blogs.mapper.CyBlogsUserMapper;
import com.copying.blogs.controller.org.service.CyBlogsUserService;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author copying
 * @date 2020-08-11
 * 用户管理具体实现
 */
@Component
public class CyBlogsUserImpl implements CyBlogsUserService {

    @Resource
    private CyBlogsUserMapper cyBlogsUserMapper;
    @Override
    public Boolean isLogin(String userName, String passWord) {
        QueryWrapper<CyBlogsUser> cyBlogsUserQueryWrapper=new QueryWrapper<>();
        cyBlogsUserQueryWrapper.lambda().eq(CyBlogsUser::getUsername,userName);

        List<CyBlogsUser> cyBlogsUsers= cyBlogsUserMapper.selectList(cyBlogsUserQueryWrapper);
        return !cyBlogsUsers.isEmpty();
    }

    @Override
    public CyBlogsUser newUser(CyBlogsUser cyBlogsUser) {
        cyBlogsUserMapper.insert(cyBlogsUser);
        return cyBlogsUser;
    }

}
