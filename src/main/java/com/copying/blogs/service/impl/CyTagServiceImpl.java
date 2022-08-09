package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyTagMapper;
import com.copying.blogs.model.entity.CyTag;
import com.copying.blogs.service.CyTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CyTagServiceImpl :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Service
public class CyTagServiceImpl extends ServiceImpl<CyTagMapper, CyTag> implements CyTagService {

    @Resource
    private CyTagMapper cyTagMapper;

    @Override
    public List<CyTag> getIndexTags() {
        return cyTagMapper.getIndexTag();
    }
}
