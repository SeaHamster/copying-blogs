package com.copying.blogs.controller.admin;


import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyBlogsType;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogsTypeService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api
@RestController
@RequestMapping("/admin/type")
public class CyBlogsTypeController {
    @Resource
    private CyBlogsTypeService cyBlogsTypeService;

    @MyLog
    @PreAuthorize("hasAuthority('content:type:add')")
    @PostMapping("/add")
    public JsonResult<?> add(@Validated @RequestBody CyBlogsType type) {
        boolean bool = cyBlogsTypeService.save(type);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:type:edit')")
    @PostMapping("/update")
    public JsonResult<?> update(@Validated @RequestBody CyBlogsType type) {
        if (Objects.isNull(type.getTypeId())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyBlogsTypeService.updateById(type);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:type:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult<?> removeById(@PathVariable("idNum") Long tyId) {
        boolean bool = cyBlogsTypeService.removeById(tyId);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }


    @PreAuthorize("hasAuthority('content:type:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult<?> getById(@PathVariable("idNum") Long tyId) {
        CyBlogsType type = cyBlogsTypeService.getById(tyId);
        return Result.success(type, ResultCode.SUCCESS);
    }


    @PreAuthorize("hasAuthority('content:type:query')")
    @GetMapping("/list")
    public JsonResult<?> list() {
        List<CyBlogsType> typeList = cyBlogsTypeService.list();
        return Result.success(typeList, ResultCode.SUCCESS);
    }
}
