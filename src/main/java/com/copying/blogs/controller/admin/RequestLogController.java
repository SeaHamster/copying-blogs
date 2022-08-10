package com.copying.blogs.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.copying.blogs.aspect.MyLog;
import com.copying.blogs.model.entity.CyRequestLog;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyRequestLogService;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/admin/log")
public class RequestLogController {
    @Autowired
    private CyRequestLogService cyRequestLogService;

    /**
     * 查询日志列表
     */

    @PreAuthorize("hasAuthority('content:requestlog:query')")
    @GetMapping("/list")
    public JsonResult<?> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "beginTime", required = false) String beginTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "ipAddress", required = false) String ipAddress) {
        PageInfo<CyRequestLog> listByPage = cyRequestLogService.getListByPage(pageNum, pageSize,
                new LambdaQueryWrapper<CyRequestLog>().eq(Strings.isNotBlank(ipAddress), CyRequestLog::getIpAddress, ipAddress)
                        .between(Strings.isNotBlank(beginTime) && Strings.isNotBlank(endTime),
                                CyRequestLog::getCreateTime, beginTime, endTime)
                        .orderByDesc(CyRequestLog::getCreateTime));
        return Result.success(listByPage, ResultCode.SUCCESS);
    }

    /**
     * 删除日志
     */
    @MyLog
    @PreAuthorize("hasAuthority('content:requestlog:remove')")
    @GetMapping("/delete/{logIds}")
    public JsonResult<?> remove(@PathVariable Long[] logIds) {

        boolean bool = cyRequestLogService.removeByIds(Arrays.asList(logIds));
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }
}
