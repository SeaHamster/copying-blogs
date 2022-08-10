package com.copying.blogs.controller;

import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.service.CyBlogService;
import com.copying.blogs.service.CyCacheService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * IndexController : 首页
 * Date: 2022/8/6
 *
 * @author copying
 */

@Api
@Controller
public class IndexController {


    @Resource
    private CyBlogService cyBlogService;
    @Resource
    private CyCacheService cyCacheService;

    @MyLog
    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, value = "title") String title, Model model) {
        model.addAttribute("page", cyCacheService.getIndexPage(title, pageNum));
        model.addAttribute("types", cyCacheService.getIndexTypes());
        model.addAttribute("tags", cyCacheService.getIndexTags());
        model.addAttribute("blogsCount", cyCacheService.getPushedBlogNum());
        model.addAttribute("typesCount", cyCacheService.getTypeNum());
        model.addAttribute("tagsCount", cyCacheService.getTagNum());
        model.addAttribute("commentsCount", cyCacheService.getCommentNum());
        model.addAttribute("user", cyCacheService.getAdminInfo());
        return "index";
    }


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @MyLog
    @GetMapping("/blog/{blogId}")
    public String blog(@PathVariable Long blogId, Model model) {
        CyBlog blog = cyBlogService.findFullById(blogId);
        cyBlogService.addViews(blogId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("user", cyCacheService.getAdminInfo());
        return "blog";
    }

}
