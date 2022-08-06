package com.copying.blogs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.dto.TimeLineBlog;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * CyBlogService :
 * Date: 2022/8/6
 *
 * @author copying
 */
public interface CyBlogService extends IService<CyBlog> {

    PageInfo<CyBlog> getIndexPage(String title, Integer pageNum);

    PageInfo<CyBlog> getPageByType(Integer pageNum, Long typeId);

    PageInfo<CyBlog> getPageByTag(Integer pageNum, Long tagId);

    PageInfo<CyBlog> getListByPage(Integer pageNum, Integer pageSize);

    PageInfo<CyBlog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlog> queryWrapper);

    boolean setPublished(Long blogId, boolean flag);

    CyBlog findFullById(Long blogId);

    CyBlog findBlogWithTagIdsById(Long blogId);

    boolean addViews(Long blogId);

    Map<String,List<TimeLineBlog>> findTimeLine();

    Boolean updateBlogWithTag(CyBlog blog);

    Boolean addBlogWithTag(CyBlog blog);

    Boolean deleteBlogWithTag(Long blogId);

    List<CyBlogsComment> getCommentWithChildById(Long blogId);

}
