package top.copying.blogs.photo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.photo.service.PhotoProcessService;
import top.copying.blogs.util.ApiResult;
import top.copying.blogs.util.IpUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理图片功能接口
 * @author copying
 * @date 2020-08-14
 */
@RestController
@Api(value = "photo tools",tags = {"图片处理工具接口"})
@RequestMapping("/photo")
public class PhotoProcessController {

    @Resource
    private PhotoProcessService photoProcessService;
    @ApiOperation(value = "获取处理后的图片",notes = "获取处理后的图片")
    @GetMapping("/getPhoto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId",value = "fileId",type = "String"),
            @ApiImplicitParam(name = "图片规格",value = "fileClass",type = "int")
    })
    public ApiResult<?> getFinishedPhoto(String fileId,int fileClass){
        return new ApiResult<>(photoProcessService.getFinishedPhoto(fileId,fileClass));
    }

    @ApiOperation(notes = "上传图片",value = "上传文件")
    @PostMapping("/upLoadPhoto")
    public ApiResult<?> upLoadPhoto(MultipartFile file,HttpServletRequest request){
        return new ApiResult<>(photoProcessService.upLoadPhoto(file, IpUtil.getIpAddr(request)));
    }


}
