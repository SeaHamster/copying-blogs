package com.copying.blogs.service;


import com.copying.blogs.model.dto.TimeLineBlog;
import com.copying.blogs.model.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface CyCacheService {

    PageInfo<CyBlog> getIndexPage(String title, Integer pageNum);
    List<CyBlogsType> getIndexTypes();
    List<CyTag> getIndexTags();
    Integer getPushedBlogNum();
    Integer getTypeNum();
    Integer getTagNum();
    Integer getCommentNum();
    CyBlogsUser getAdminInfo();
    List<String> getPermissionList(Long userId);
    PageInfo<CyBlog> getPageByType(Integer pageNum,Long typeId);
    PageInfo<CyBlog> getPageByTag(Integer pageNum,Long tagId);
    Map<String,List<TimeLineBlog>> findTimeLine();
    List<CyBlogsFriend> getIndexFriends();
}
