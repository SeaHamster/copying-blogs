package com.copying.blogs.controller.admin;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyBlogsComment;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogService;
import com.copying.blogs.service.CyBlogsCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api
@RestController
@RequestMapping("/admin/comment")
public class CyBlogsCommentController {
    @Resource
    private CyBlogsCommentService cyBlogsCommentService;
    @Resource
    private CyBlogService CyBlogService;

    @PreAuthorize("hasAuthority('content:comment:query')")
    @GetMapping("/getCommentWithChildById/{idNum}")
    public JsonResult<?> getCommentWithChildById(@PathVariable("idNum") Long blogId) {
        List<CyBlogsComment> commentList = CyBlogService.getCommentWithChildById(blogId);
        return Result.success(commentList, ResultCode.SUCCESS);

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:comment:delete')")
    @PostMapping("/setDeleted")
    public JsonResult<?> setPublished(@RequestBody CyBlogsComment comment) {
        if (Objects.isNull(comment.getCommentId()) || Objects.isNull(comment.getIsDelete())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyBlogsCommentService.setDeleted(comment.getCommentId(), comment.getIsDelete());
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

}
