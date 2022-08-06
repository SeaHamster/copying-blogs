package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsUserMapper;
import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.copying.blogs.service.CyBlogsRoleService;
import com.copying.blogs.service.CyBlogsUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fzz
 * @date 2022/8/5
 */
@Service
public class CyBlogsUserServiceImpl extends ServiceImpl<CyBlogsUserMapper, CyBlogsUser> implements CyBlogsUserService {

    @Resource
    private CyBlogsUserMapper cyBlogsUserMapper;

    @Resource
    private CyBlogsRoleService cyBlogsRoleService;


    @Override
    public CyBlogsUser getAdminInfo() {
        //roleId自行修改
        return this.getOne(new LambdaQueryWrapper<CyBlogsUser>().eq(CyBlogsUser::getRoleId, 1));
    }

    @Override
    public CyBlogsUser verifyLogin(String username, String password) {
        final CyBlogsUser user = this.getOne(new LambdaQueryWrapper<CyBlogsUser>().eq(CyBlogsUser::getUsername, username)
                .eq(CyBlogsUser::getPassword, password));
        user.setPermissionList(this.getPermissionList(user.getUserId()));
        return user;
    }

    @Override
    public List<String> getPermissionList(Long usId) {
        if (this.isAdmin(usId)) {
            return cyBlogsUserMapper.getPermissionsAll();
        } else {
            return cyBlogsUserMapper.getPermissionsById(usId);
        }

    }

    @Override
    public boolean isAdmin(Long usId) {
        return cyBlogsUserMapper.getRoleKeyById(usId).equals("admin");
    }

    @Override
    public PageInfo<CyBlogsUser> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlogsUser> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyBlogsUser> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public CyBlogsUser getMyUserById(Long usId) {
        final CyBlogsUser user = cyBlogsUserMapper.selectById(usId);
        user.setRole(cyBlogsRoleService.getById(user.getRoleId()));
        user.setPermissionList(this.getPermissionList(usId));
        return user;
    }

    @Override
    public CyBlogsUser generateUserByGithubUsId(Long githubUsId, CyBlogsUser saveUser) {
        final CyBlogsUser one = this.getOne(new LambdaQueryWrapper<CyBlogsUser>().eq(CyBlogsUser::getOauthUsId, githubUsId));
        if (one != null) {
            one.setRole(cyBlogsRoleService.getById(one.getRoleId()));
            one.setPermissionList(this.getPermissionList(one.getUserId()));
            return one;
        } else {
            this.saveOrUpdate(saveUser);
            saveUser.setRole(cyBlogsRoleService.getById(saveUser.getRoleId()));
            saveUser.setPermissionList(this.getPermissionList(saveUser.getUserId()));
            return saveUser;
        }
    }

}
