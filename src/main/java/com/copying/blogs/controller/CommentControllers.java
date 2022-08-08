package com.copying.blogs.controller;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.service.CyBlogService;
import com.copying.blogs.service.CyBlogsCommentService;
import com.copying.blogs.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentControllers {

    @Resource
    private CyBlogsCommentService cyBlogsCommentService;


    @Resource
    private CyBlogService cyBlogService;

    @MyLog
    @GetMapping("/comments/{blId}")
    public String comments(@PathVariable Long blId, Model model) {
        CyBlog blog = cyBlogService.findFullById(blId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        return "blog :: commentList";
    }

    @MyLog
    @PostMapping("/comments")
    public String postComments(CyBlogsComment comment, HttpServletRequest request) {
        //保存评论
        if (comment.getParentId() <= -1)
            comment.setParentId(null);
        String ipAddress = IpUtil.getIpAddress(request);
        comment.setIpAddress(ipAddress);
        cyBlogsCommentService.saveOrUpdate(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }

}
