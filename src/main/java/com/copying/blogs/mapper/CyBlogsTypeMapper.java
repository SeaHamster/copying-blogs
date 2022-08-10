package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.entity.CyBlogsType;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

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
