package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.copying.blogs.aspect.MyCache;
import com.copying.blogs.model.dto.TimeLineBlog;
import com.copying.blogs.model.entity.*;
import com.copying.blogs.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CyCacheServiceImpl implements CyCacheService {
    @Resource
    private CyBlogService cyBlogService;
    @Resource
    private CyBlogsUserService iUserService;
    @Resource
    private CyBlogsCommentService cyBlogsCommentService;
    @Resource
    private CyBlogsTypeService cyBlogsTypeService;
    @Resource
    private CyTagService cyTagService;
    @Resource
    private CyBlogsFriendService cyBlogsFriendService;

    @Override
    @MyCache
    public PageInfo<CyBlog> getIndexPage(String title, Integer pageNum) {
        return cyBlogService.getIndexPage(title, pageNum);
    }

    @Override
    @MyCache
    public List<CyBlogsType> getIndexTypes() {
        return cyBlogsTypeService.getIndexTypes();
    }

    @Override
    @MyCache
    public List<CyTag> getIndexTags() {
        return cyTagService.getIndexTags();
    }

    @Override
    @MyCache
    public Integer getPushedBlogNum() {
        return cyBlogService.count(new LambdaQueryWrapper<CyBlog>().eq(CyBlog::getPublished, true));
    }

    @Override
    @MyCache
    public Integer getTypeNum() {
        return  cyBlogsTypeService.count();
    }

    @Override
    @MyCache
    public Integer getTagNum() {
        return  cyTagService.count();
    }

    @Override
    @MyCache
    public Integer getCommentNum() {
        return cyBlogsCommentService.count();
    }

    @Override
    @MyCache
    public CyBlogsUser getAdminInfo() {
        return iUserService.getAdminInfo();
    }

    @Override
    @MyCache
    public List<String> getPermissionList(Long usId) {
        return iUserService.getPermissionList(usId);
    }

    @Override
    @MyCache
    public PageInfo<CyBlog> getPageByType(Integer pageNum, Long typeId) {
        return cyBlogService.getPageByType(pageNum,typeId);
    }

    @Override
    @MyCache
    public PageInfo<CyBlog> getPageByTag(Integer pageNum, Long tagId) {
        return cyBlogService.getPageByTag(pageNum, tagId);
    }

    @Override
    @MyCache
    public Map<String,List<TimeLineBlog>> findTimeLine() {
        return cyBlogService.findTimeLine();
    }

    @Override
    @MyCache
    public List<CyBlogsFriend> getIndexFriends() {
        return cyBlogsFriendService.list();
    }

}
