package com.copying.blogs.controller.admin;

import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogsFileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author fzz
 * @date 2022/8/16
 */

@Api("文件管理相关接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private CyBlogsFileService cyBlogsFileService;

    @PostMapping("/upload")
    public JsonResult<?> uploadImg(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String url = cyBlogsFileService.uploadFile(file);
            return Result.success(url, ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.DATA_IS_WRONG);
    }

}
