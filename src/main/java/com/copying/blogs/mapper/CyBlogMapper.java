package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.dto.TimeLineBlog;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.model.entity.CyBlogsType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CyBlogMapper extends BaseMapper<CyBlog> {
    /***
     *  //查询首页Blog信息(关联标签，类型)
     */
    @Results(id = "blogInfo", value = {
            @Result(id = true, property = "blogId", column = "blog_id"),
            @Result(property = "tags", column = "blog_id", many = @Many(select = "com.copying.blogs.mapper.CyBlogMapper.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "type_id", one = @One(select = "com.copying.blogs.mapper.CyBlogMapper.findTypeByBlog",
                    fetchType = FetchType.DEFAULT)),
    })
    @Select("<script>" +
            "select blog_id,title,outline,background_image,recommend,commentabled,published,views,type_id,create_time,update_time from cy_blog where published=true" +
            "<if test='title!=null and title!=\"\"'>" +
            "AND title like concat('%',#{title},'%')" +
            "</if>" +
            "order by create_time desc" +
            "</script>"
    )
    List<CyBlog> findIndexPage(@Param("title") String title);

    /***
     *  //查询首页Blog信息(关联标签，类型)
     *  根据tag查找Blog(发布的)
     */
    @Select("select blog_id,title,outline,background_image,recommend,commentabled,published,views,type_id,create_time,update_time from cy_blog where type_id = #{typeId} and published=true order by create_time desc")
    @ResultMap(value = "blogInfo")
    List<CyBlog> getPageByType(Long typeId);

    /***
     *  //查询首页Blog信息(关联标签，类型)
     *  根据tag查找Blog(发布的)
     */
    @Select("SELECT b.blog_id,b.title,b.outline,b.background_image,b.recommend,b.commentabled,b.published,b.views,b.type_id,b.create_time,b.update_time FROM cy_blog AS b LEFT JOIN t_blog_tag AS bt ON b.blog_id=bt.blog_id WHERE bt.tag_id=#{tagId} and b.published=true order by b.create_time desc")
    @ResultMap(value = "blogInfo")
    List<CyBlog> getPageByTag(Long tagId);


    /***
     *  //查询Blog完整信息(关联标签，类型，评论)
     */
    @Results(value = {
            @Result(id = true, property = "blogId", column = "blog_id"),
            @Result(property = "tags", column = "blog_id", many = @Many(select = "com.copying.blogs.mapper.CyBlogMapper.findTagsByBlog",
                    fetchType = FetchType.DEFAULT)),
            @Result(property = "type", column = "type_id", one = @One(select = "com.copying.blogs.mapper.CyBlogMapper.findTypeByBlog",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("select * from cy_blog where blog_id=#{blogId} ")
    CyBlog findFullBlogById(Long blogId);

    /***
     *  //查询Blog信息(关联标签ID)
     */
    @Results(value = {
            @Result(id = true, property = "blogId", column = "blog_id"),
            @Result(property = "tagIds", column = "blog_id", one = @One(select = "com.copying.blogs.mapper.CyBlogMapper.findTagIdsById",
                    fetchType = FetchType.DEFAULT))
    })
    @Select("select * from cy_blog where blog_id=#{blogId} ")
    CyBlog findBlogWithTagIdsById(Long blogId);

    /***
     *  //时间线搜索
     */
    @Results(value = {
            @Result(id = true, property = "blogId", column = "blog_id"),
    })
    @Select("SELECT  DATE_FORMAT(create_time,'%c-%d') AS 'date', " +
            "DATE_FORMAT(create_time,'%Y-%m') AS 'month',blog_id,title " +
            " FROM cy_blog WHERE published=TRUE GROUP BY blog_id,DATE_FORMAT(create_time,'%Y-%m') ORDER BY create_time desc")
    List<TimeLineBlog> findTimeLine();

    /***
     *  //删除blog_tag关联信息
     */
    @Delete("delete from t_blog_tag where blog_id = #{blogId}")
    int deleteBlogTagBatch(@Param("blogId") Long blogId);

    /***
     *  //添加blog_tag关联信息
     */
    @Insert("<script>" +
            "INSERT INTO t_blog_tag(blog_id,tag_id) VALUES " +
            "<foreach collection='list' item='tagId' separator=','>" +
            " (#{blogId},#{tagId})" +
            "</foreach>" +
            "</script>")
    int addBlogTagBatch(@Param("blogId") Long blogId, @Param("list") List<Long> list);

    /***
     *  //访问量+1
     */
    @Update("update cy_blog set views=views+1,update_time=update_time where blog_id = #{blogId}")
    int addViewsById(@Param("blogId") Long blogId);
    //---------------------------------------------------查询需要用到的子查询-------------------------------------------------------------

    /***
     *  //根据博客查询对应的tags（内联查询）
     */
    @Select("SELECT * FROM cy_tag WHERE cy_tag.`tag_id` IN (SELECT bt.`tag_id` FROM t_blog_tag AS bt WHERE bt.`blog_id`=#{blogId})")
    List<CyBlogsType> findTagsByBlog(Long blogId);


    /***
     *  //根据博客查询对应的type
     */
    @Select("select * from cy_blogs_type where type_id= #{typeId}")
    CyBlogsType findTypeByBlog(Long typeId);

    /***
     *  //查询Blog关联TagId
     */
    @Select("select tag_id from t_blog_tag where blog_id=#{blogId}")
    Long[] findTagIdsById(Long blogId);
    //----------------------------------------------------------------------------------------------------------------------

    /***
     *  //根据博客查询对应的comments
     */
    @Results(id = "commentMap", value = {
            @Result(id = true, property = "commentId", column = "comment_id"),
            @Result(property = "parentId", column = "parent_id"),
    })
    @Select("select * from cy_blogs_comment where blog_id=#{blogId}")
    List<CyBlogsComment> findCommentByBlog(Long blogId);


//    /***
//     *  //查询评论的子评论
//     */
//    @ResultMap("commentMap")
//    @Select("select * from cy_blogs_comment where parent_id=#{commentId} order by create_time desc")
//    Comment findChildListByComment(Long commentId);

//    /***
//     *  //查询评论的父评论
//     */
//    @Select("select * from cy_blogs_comment where comment_id=#{parentId}")
//    Comment findParentByComment(Long parentId);
//    //----------------------------------------------------------------------------------------------------------------------

}
