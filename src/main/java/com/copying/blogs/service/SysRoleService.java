package com.copying.blogs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author fzz
 * @date 2022/8/5
 */
public interface SysRoleService extends IService<SysRole> {
    PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper);

    Boolean addRoleWithMenuBatch(SysRole CyBlogsRole);

    Boolean updateRoleWithMenuBatch(SysRole CyBlogsRole);

    Boolean deleteByIdsWithMenuBatch(List<Long> roleIds);
}
