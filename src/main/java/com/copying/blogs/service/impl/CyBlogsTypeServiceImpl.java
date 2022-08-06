package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogsTypeMapper;
import com.copying.blogs.model.entity.CyBlogsType;
import com.copying.blogs.service.CyBlogsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CyBlogsTypeServiceImpl :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Service
public class CyBlogsTypeServiceImpl extends ServiceImpl<CyBlogsTypeMapper, CyBlogsType> implements CyBlogsTypeService {

    @Resource
    private CyBlogsTypeMapper cyBlogsTypeMapper;

    @Override
    public List<CyBlogsType> getIndexTypes() {
        return cyBlogsTypeMapper.getIndexTypes();
    }

}