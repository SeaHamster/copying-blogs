package com.copying.blogs.controller;

import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.service.CyBlogService;
import com.copying.blogs.service.CyCacheService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    /**
     * 首页
     * @param model model
     * @return java.lang.String
     */
//    @MyLog
//    @GetMapping("/")
//    public String blogs(Model model) {
//        model.addAttribute("user", cyCacheService.getAdminInfo());
//        return "index";
//    }

    /**
     * 联系我
     * @return java.lang.String
     */
    @MyLog
    @PostMapping("/contactMe")
    public JsonResult<?> contactMe(String name, String email, String subject, String text){
        if(StringUtils.isEmpty(subject)){
            return Result.fail("请输入一个主题");
        }
        if(StringUtils.isEmpty(text)){
            return Result.fail("请输入内容");
        }
        System.out.println(name);
        System.out.println(email);
        System.out.println(subject);
        System.out.println(text);

        return Result.success("成功，稍后将联系您");
    }

    /**
     * 文章列表
     * @param pageNum pageNum
     * @param title title
     * @param model model
     * @return java.lang.String
     */
    @MyLog
//    @GetMapping("/blogs")
    @GetMapping("/")
    public String blogs(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, value = "title") String title, Model model) {
        model.addAttribute("page", cyCacheService.getIndexPage(title, pageNum));
        model.addAttribute("types", cyCacheService.getIndexTypes());
        model.addAttribute("tags", cyCacheService.getIndexTags());
        model.addAttribute("blogsCount", cyCacheService.getPushedBlogNum());
        model.addAttribute("typesCount", cyCacheService.getTypeNum());
        model.addAttribute("tagsCount", cyCacheService.getTagNum());
        model.addAttribute("commentsCount", cyCacheService.getCommentNum());
        model.addAttribute("user", cyCacheService.getAdminInfo());
        return "blogs";
    }


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @MyLog
    @GetMapping("/blog/{blogId}")
    public String blog(@PathVariable Long blogId, Model model) {
        CyBlog blog = cyBlogService.findFullById(blogId);
        if(blog == null){
            throw new CustomizeException("资源不存在！");
        }
        cyBlogService.addViews(blogId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("user", cyCacheService.getAdminInfo());
        return "blog";
    }

}
