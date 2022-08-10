package com.copying.blogs.controller.admin;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyBlogsFriend;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogsFriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/admin/friend")
public class CyBlogsFriendController {
    @Autowired
    private CyBlogsFriendService cyBlogsFriendService;

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:add')")
    @PostMapping("/add")
    public JsonResult<?> add(@Validated @RequestBody CyBlogsFriend friend) {
        boolean bool = cyBlogsFriendService.save(friend);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:edit')")
    @PostMapping("/update")
    public JsonResult<?> update(@Validated @RequestBody CyBlogsFriend friend) {
        boolean bool = cyBlogsFriendService.updateById(friend);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult<?> removeById(@PathVariable("idNum") Long frId) {
        boolean bool = cyBlogsFriendService.removeById(frId);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    @PreAuthorize("hasAuthority('content:friend:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult<?> getById(@PathVariable("idNum") Long frId) {
        CyBlogsFriend friend = cyBlogsFriendService.getById(frId);
        return Result.success(friend, ResultCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('content:friend:query')")
    @GetMapping("/list")
    public JsonResult<?> list() {
        List<CyBlogsFriend> friendList = cyBlogsFriendService.list();
        return Result.success(friendList, ResultCode.SUCCESS);
    }
}
