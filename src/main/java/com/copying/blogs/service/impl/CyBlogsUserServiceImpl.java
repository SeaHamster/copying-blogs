package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsUserMapper;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.copying.blogs.service.SysRoleService;
import com.copying.blogs.service.CyBlogsUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private SysRoleService sysRoleService;


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
    public List<String> getPermissionList(Long userId) {
        if (this.isAdmin(userId)) {
            return cyBlogsUserMapper.getPermissionsAll();
        } else {
            return cyBlogsUserMapper.getPermissionsById(userId);
        }

    }

    @Override
    public boolean isAdmin(Long userId) {
        return cyBlogsUserMapper.getRoleKeyById(userId).equals("admin");
    }

    @Override
    public PageInfo<CyBlogsUser> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlogsUser> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyBlogsUser> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public CyBlogsUser getMyUserById(Long userId) {
        final CyBlogsUser user = cyBlogsUserMapper.selectById(userId);
        user.setRole(sysRoleService.getById(user.getRoleId()));
        user.setPermissionList(this.getPermissionList(userId));
        return user;
    }

    @Override
    public CyBlogsUser generateUserByGithubUsId(Long githubUsId, CyBlogsUser saveUser) {
        final CyBlogsUser one = this.getOne(new LambdaQueryWrapper<CyBlogsUser>().eq(CyBlogsUser::getOauthUsId, githubUsId));
        if (one != null) {
            one.setRole(sysRoleService.getById(one.getRoleId()));
            one.setPermissionList(this.getPermissionList(one.getUserId()));
            return one;
        } else {
            this.saveOrUpdate(saveUser);
            saveUser.setRole(sysRoleService.getById(saveUser.getRoleId()));
            saveUser.setPermissionList(this.getPermissionList(saveUser.getUserId()));
            return saveUser;
        }
    }

}
