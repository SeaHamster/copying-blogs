package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyRequestLogMapper;
import com.copying.blogs.model.entity.CyRequestLog;
import com.copying.blogs.service.CyRequestLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CyRequestLogServiceImpl :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Service
public class CyRequestLogServiceImpl extends ServiceImpl<CyRequestLogMapper, CyRequestLog> implements CyRequestLogService {
    @Override
    public PageInfo<CyRequestLog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyRequestLog> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyRequestLog> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }
}
