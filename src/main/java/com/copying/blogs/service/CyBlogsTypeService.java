package com.copying.blogs.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyBlogsType;

import java.util.List;

public interface CyBlogsTypeService extends IService<CyBlogsType> {
    List<CyBlogsType> getIndexTypes();

}
