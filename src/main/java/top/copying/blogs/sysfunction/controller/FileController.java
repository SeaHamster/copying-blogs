package top.copying.blogs.sysfunction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.sysfunction.service.FileService;
import top.copying.blogs.util.ApiResult;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author copying
 * @date 2020-08-17 08:28:38
 * 文件功能接口
 */
@RestController
@RequestMapping("/file")
@Api(value = "file tools",tags = "文件功能")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation(notes = "上传文件",value = "上传文件")
    @PostMapping("/upLoad")
    public ApiResult<?> upLoadFile(@NotNull MultipartFile file ){
        return new ApiResult<>(fileService.upLoadFile(file));
    }


}