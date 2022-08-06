package com.copying.blogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyTag;

import java.util.List;

/**
 * CyTagService :
 * Date: 2022/8/6
 *
 * @author copying
 */
public interface CyTagService extends IService<CyTag> {
    List<CyTag> getIndexTags();

}