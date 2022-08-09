package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.SysRoleMapper;
import com.copying.blogs.model.entity.SysRole;
import com.copying.blogs.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author fzz
 * @date 2022/8/5
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional
    public Boolean addRoleWithMenuBatch(SysRole cyBlogsRole) {
        this.save(cyBlogsRole);
        if (Objects.nonNull(cyBlogsRole.getMenuIds()) && cyBlogsRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(cyBlogsRole.getMenuIds());
            sysRoleMapper.addRoleMenuBatch(cyBlogsRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateRoleWithMenuBatch(SysRole cyBlogsRole) {
        this.updateById(cyBlogsRole);
        sysRoleMapper.deleteRoleMenuBatch(cyBlogsRole.getRoleId());
        if (Objects.nonNull(cyBlogsRole.getMenuIds()) && cyBlogsRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(cyBlogsRole.getMenuIds());
            sysRoleMapper.addRoleMenuBatch(cyBlogsRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteByIdsWithMenuBatch(List<Long> roleIds) {
        this.removeByIds(roleIds);
        roleIds.forEach(id -> sysRoleMapper.deleteRoleMenuBatch(id));
        return true;
    }
}
