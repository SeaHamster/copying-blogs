package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.entity.CyBlogsType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CyBlogsTypeMapper extends BaseMapper<CyBlogsType> {
    @Results(value = {
            @Result(id = true, property = "typeId", column = "type_id"),
            @Result(property = "blogsNum", column = "type_id", many = @Many(select = "com.copying.blogs.mapper.CyBlogsTypeMapper.getBlogNumByType",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from cy_blogs_type")
    List<CyBlogsType> getIndexTypes();
//----------------------------------------------------------------------------------------------------------------------
    @Select("select count(*) from cy_blog where type_id=#{typeId}")
    Integer getBlogNumByType(Long typeId);
}
