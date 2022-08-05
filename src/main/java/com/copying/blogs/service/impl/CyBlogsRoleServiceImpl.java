package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsRoleMapper;
import com.copying.blogs.model.entity.CyBlogsRole;
import com.copying.blogs.service.CyBlogsRoleService;
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
public class CyBlogsRoleServiceImpl extends ServiceImpl<CyBlogsRoleMapper, CyBlogsRole> implements CyBlogsRoleService {

    @Resource
    private CyBlogsRoleMapper cyBlogsRoleMapper;

    @Override
    public PageInfo<CyBlogsRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlogsRole> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyBlogsRole> list = this.list(queryWrapper);
        PageInfo<CyBlogsRole> result = new PageInfo<>(list);
        return result;
    }

    @Override
    @Transactional
    public Boolean addRoleWithMenuBatch(CyBlogsRole cyBlogsRole) {
        this.save(cyBlogsRole);
        if (Objects.nonNull(cyBlogsRole.getMenuIds()) && cyBlogsRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(cyBlogsRole.getMenuIds());
            cyBlogsRoleMapper.addRoleMenuBatch(cyBlogsRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateRoleWithMenuBatch(CyBlogsRole cyBlogsRole) {
        this.updateById(cyBlogsRole);
        cyBlogsRoleMapper.deleteRoleMenuBatch(cyBlogsRole.getRoleId());
        if (Objects.nonNull(cyBlogsRole.getMenuIds()) && cyBlogsRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(cyBlogsRole.getMenuIds());
            cyBlogsRoleMapper.addRoleMenuBatch(cyBlogsRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteByIdsWithMenuBatch(List<Long> roleIds) {
        this.removeByIds(roleIds);
        roleIds.stream().forEach(id -> {
            cyBlogsRoleMapper.deleteRoleMenuBatch(id);
        });
        return true;
    }
}
