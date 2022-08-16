package com.copying.blogs.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.model.entity.CyBlog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogService;
import com.copying.blogs.service.CyBlogsCommentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("/admin/blog")
public class CyBlogController {
    @Resource
    private CyBlogService cyBlogService;
    @Resource
    private CyBlogsCommentService cyBlogsCommentService;

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:add')")
    @PostMapping("/add")
    public JsonResult<?> add(@Validated @RequestBody CyBlog blog) {
        boolean bool = cyBlogService.addBlogWithTag(blog);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:edit')")
    @PostMapping("/update")
    public JsonResult<?> update(@Validated @RequestBody CyBlog blog) {
        if (Objects.isNull(blog.getBlogId())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyBlogService.updateBlogWithTag(blog);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult<?> removeById(@PathVariable("idNum") Long blogId) {

        boolean bool = cyBlogService.deleteBlogWithTag(blogId);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    @PreAuthorize("hasAuthority('content:blog:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult<?> getById(@PathVariable("idNum") Long blogId) {
        CyBlog blog = cyBlogService.findBlogWithTagIdsById(blogId);
        return Result.success(blog, ResultCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('content:blog:query')")
    @GetMapping("/list")
    public JsonResult<?> list(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "limit", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "type", required = false) Long tyId
    ) {
        PageInfo<CyBlog> listByPage = cyBlogService.getListByPage(pageNum, pageSize,
                new LambdaQueryWrapper<CyBlog>().eq(Objects.nonNull(tyId), CyBlog::getTypeId, tyId)
                        .like(Strings.isNotBlank(title), CyBlog::getTitle, title)
                        .select(CyBlog::getTitle, CyBlog::getTypeId, CyBlog::getPublished, CyBlog::getViews,
                                CyBlog::getBlogId, CyBlog::getBackgroundImage, CyBlog::getCreateTime,
                                CyBlog::getOutline, CyBlog::getUpdateTime).orderByDesc(CyBlog::getCreateTime));
        return Result.success(listByPage, ResultCode.SUCCESS);
    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:setPub')")
    @PostMapping("/setPublished")
    public JsonResult<?> setPublished(@RequestBody CyBlog blog) {
        if (Objects.isNull(blog.getBlogId()) || Objects.isNull(blog.getPublished())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyBlogService.setPublished(blog.getBlogId(), blog.getPublished());
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    @GetMapping("/hasCommentDic")
    public JsonResult<?> getBlogHasCommentDictionaries() {
        List<Object> blIds = cyBlogsCommentService.listObjs(new LambdaQueryWrapper<CyBlogsComment>()
                .select(CyBlogsComment::getBlogId)).stream().distinct().collect(Collectors.toList());
        if(CollectionUtils.isEmpty(blIds)){
            throw new CustomizeException("未获取到任何评论");
        }
        List<CyBlog> blogListDic = cyBlogService.list(new LambdaQueryWrapper<CyBlog>().in(CyBlog::getBlogId, blIds)
                .select(CyBlog::getBlogId, CyBlog::getTitle));
        return Result.success(blogListDic, ResultCode.SUCCESS);
    }

}
