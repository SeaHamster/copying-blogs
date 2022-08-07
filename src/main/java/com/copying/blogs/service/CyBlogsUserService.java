package com.copying.blogs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fzz
 * @date 2022/8/5
 */
public interface CyBlogsUserService extends IService<CyBlogsUser> {

    CyBlogsUser getAdminInfo();

    CyBlogsUser verifyLogin(String username, String password);

    List<String> getPermissionList(Long userId);

    boolean isAdmin(Long userId);

    PageInfo<CyBlogsUser> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlogsUser> queryWrapper);

    CyBlogsUser getMyUserById(Long userId);

    CyBlogsUser generateUserByGithubUsId(Long githubUsId, CyBlogsUser saveUser);
}
