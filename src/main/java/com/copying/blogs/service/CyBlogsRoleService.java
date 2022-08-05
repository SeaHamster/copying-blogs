package com.copying.blogs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyBlogsRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author fzz
 * @date 2022/8/5
 */
public interface CyBlogsRoleService  extends IService<CyBlogsRole> {
    PageInfo<CyBlogsRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlogsRole> queryWrapper);

    Boolean addRoleWithMenuBatch(CyBlogsRole CyBlogsRole);

    Boolean updateRoleWithMenuBatch(CyBlogsRole CyBlogsRole);

    Boolean deleteByIdsWithMenuBatch(List<Long> roleIds);
}
