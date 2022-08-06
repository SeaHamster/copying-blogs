package com.copying.blogs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyRequestLog;
import com.github.pagehelper.PageInfo;


public interface CyRequestLogService extends IService<CyRequestLog> {
    PageInfo<CyRequestLog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyRequestLog> queryWrapper);
}
