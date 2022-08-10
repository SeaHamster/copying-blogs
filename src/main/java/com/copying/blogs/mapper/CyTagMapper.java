package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyTag;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * CyTagMapper :
 * Date: 2022/8/6
 *
 * @author copying
 */
public interface CyTagMapper extends BaseMapper<CyTag> {
    @Results(value = {
            @Result(id = true, property = "tagId", column = "tag_id"),
            @Result(property = "blogsNum", column = "tag_id", many = @Many(select = "com.copying.blogs.mapper.CyTagMapper.getBlogNumByTag",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from cy_tag")
    List<CyTag> getIndexTag();

    //详细查询所有
    @Results(value = {
            @Result(id = true, property = "tagId", column = "tag_id"),
            @Result(property = "blogs", column = "tag_id", many = @Many(select = "com.copying.blogs.mapper.CyTagMapper.findBlogByTagPublished",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("select * from cy_tag")
    List<CyTag> findTagPage();
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select b.* from cy_blog as b left join cy_blog_tag as bt on bt.blog_id=b.blog_id where b.published=true and bt.tag_id=#{tagId}")
    CyBlog findBlogByTagPublished(Long tagId);
    //----------------------------------------------------------------------------------------------------------------------
    @Select("select count(*) from cy_blog_tag where tag_id=#{tagId}")
    Integer getBlogNumByTag(Long tagId);
}
