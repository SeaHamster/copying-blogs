package com.copying.blogs.controller.admin;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyTag;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyTagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api
@RestController
@RequestMapping("/admin/tag")
public class CyTagController {
    @Autowired
    private CyTagService cyTagService;

    @MyLog
    @PreAuthorize("hasAuthority('content:tag:add')")
    @PostMapping("/add")
    public JsonResult<?> add(@Validated @RequestBody CyTag tag) {
        boolean bool = cyTagService.save(tag);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    @MyLog
    @PreAuthorize("hasAuthority('content:tag:edit')")
    @PostMapping("/update")
    public JsonResult<?> update(@Validated @RequestBody CyTag tag) {
        if (Objects.isNull(tag.getTagId())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyTagService.updateById(tag);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    @MyLog
    @PreAuthorize("hasAuthority('content:tag:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult<?> removeById(@PathVariable("idNum") Long taId) {
        boolean bool = cyTagService.removeById(taId);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }


    @PreAuthorize("hasAuthority('content:tag:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult<?> getById(@PathVariable("idNum") Long taId) {
        CyTag tag = cyTagService.getById(taId);
        return Result.success(tag, ResultCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('content:tag:query')")
    @GetMapping("/list")
    public JsonResult<?> list() {
        List<CyTag> tagList = cyTagService.list();
        return Result.success(tagList, ResultCode.SUCCESS);
    }
}
