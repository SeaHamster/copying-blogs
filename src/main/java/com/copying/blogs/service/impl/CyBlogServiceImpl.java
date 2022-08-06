package com.copying.blogs.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.mapper.CyBlogMapper;
import com.copying.blogs.model.dto.TimeLineBlog;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.service.CyBlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CyBlogServiceImpl extends ServiceImpl<CyBlogMapper, CyBlog> implements CyBlogService {
    @Resource
    private CyBlogMapper cyBlogMapper;

    @Override
    public PageInfo<CyBlog> getIndexPage(String title, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<CyBlog> list = cyBlogMapper.findIndexPage(title);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CyBlog> getPageByType(Integer pageNum, Long typeId) {
        PageHelper.startPage(pageNum, 5);
        List<CyBlog> list = cyBlogMapper.getPageByType(typeId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CyBlog> getPageByTag(Integer pageNum, Long tagId) {
        PageHelper.startPage(pageNum, 5);
        List<CyBlog> list = cyBlogMapper.getPageByTag(tagId);
        return new PageInfo<>(list);
    }


    @Override
    public PageInfo<CyBlog> getListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyBlog> list = this.list();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CyBlog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<CyBlog> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<CyBlog> list = this.list(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public boolean setPublished(Long blogId, boolean flag) {
        return this.update(new LambdaUpdateWrapper<CyBlog>().eq(CyBlog::getBlogId, blogId)
                .set(CyBlog::getPublished, flag));
    }

    @Override
    public CyBlog findFullById(Long blogId) {
        CyBlog blog = cyBlogMapper.findFullBlogById(blogId);
        if (blog != null) {
            blog.setComments(getCommentWithChildById(blogId));
        }
        return blog;
    }

    @Override
    public CyBlog findBlogWithTagIdsById(Long blogId) {
        return cyBlogMapper.findBlogWithTagIdsById(blogId);
    }

    @Override
    public boolean addViews(Long blogId) {
        cyBlogMapper.addViewsById(blogId);
        return true;
    }

    @Override
    public List<CyBlogsComment> getCommentWithChildById(Long blogId) {
        List<CyBlogsComment> commentList = cyBlogMapper.findCommentByBlog(blogId);
        Map<Long, List<CyBlogsComment>> commentMap = commentList.stream().filter(comment ->
                Objects.nonNull(comment.getParentId())).collect(Collectors.groupingBy(CyBlogsComment::getParentId));
        return commentList.stream().filter(comment -> Objects.isNull(comment.getParentId()))
                .peek(curComment -> {//将comment下所有子孙评论放入comment的ChildList
                    List<CyBlogsComment> childList = new ArrayList<>();
                    getChildesByCur(childList, curComment, commentMap);
                    childList.sort(Comparator.comparing(CyBlogsComment::getCreateTime).reversed());
                    curComment.setChildList(childList);
                }).sorted(Comparator.comparing(CyBlogsComment::getCreateTime).reversed()).collect(Collectors.toList());

    }

    /**
     * 递归 插入评论的父节点，子节点
     */
    private void getChildesByCur(List<CyBlogsComment> resList, CyBlogsComment curComment, Map<Long,
            List<CyBlogsComment>> commentMap) {
        if (curComment == null || curComment.getCommentId() == null) {
            return;
        }
        Long key = curComment.getCommentId();
        if (commentMap.containsKey(key)) {
            for (CyBlogsComment nextComment : commentMap.get(key)) {
                //构造父节点，用于前端显示@Parent
                nextComment.setParent(CyBlogsComment.builder().commentId(curComment.getCommentId())
                        .name(curComment.getName()).build());
                resList.add(nextComment);
                getChildesByCur(resList, nextComment, commentMap);
            }
        }
    }


    @Override
    public Map<String,List<TimeLineBlog>> findTimeLine() {
        Map<String, List<TimeLineBlog>> map = new LinkedHashMap<>();
        for (TimeLineBlog tlBlog : cyBlogMapper.findTimeLine()) {
            if (!map.containsKey(tlBlog.getMonth())) {
                map.put(tlBlog.getMonth(), new ArrayList<>());
            }
            String temMonth = tlBlog.getMonth();
            tlBlog.setMonth(null);
            map.get(temMonth).add(tlBlog);
        }
        return map;
    }

    @Override
    @Transactional
    public Boolean updateBlogWithTag(CyBlog blog) {
        this.updateById(blog);
        cyBlogMapper.deleteBlogTagBatch(blog.getBlogId());
        if (Objects.nonNull(blog.getTagIds()) && blog.getTagIds().length > 0) {
            List<Long> tagIds = Arrays.asList(blog.getTagIds());
            cyBlogMapper.addBlogTagBatch(blog.getBlogId(), tagIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean addBlogWithTag(CyBlog blog) {
        this.save(blog);
        if (Objects.nonNull(blog.getTagIds()) && blog.getTagIds().length > 0) {
            List<Long> tagIds = Arrays.asList(blog.getTagIds());
            cyBlogMapper.addBlogTagBatch(blog.getBlogId(), tagIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteBlogWithTag(Long blogId) {
        this.removeById(blogId);
        cyBlogMapper.deleteBlogTagBatch(blogId);
        return true;
    }

}
