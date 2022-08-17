package com.copying.blogs.util;

import com.copying.blogs.constants.Constants;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.mapper.CyBlogsFileMapper;
import com.copying.blogs.model.dto.FileInfo;
import com.copying.blogs.model.entity.CyBlogsFile;
import com.copying.blogs.model.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 * @author copying
 * @date 2020-08-15
 */
@Component
@Slf4j
public class FileUtil {

    @Resource
    private CyBlogsFileMapper cyBlogsFileMapper;

    /**
     * 文件上传
     * @return 文件地址
     */
    public static FileInfo fileUpLoad(MultipartFile file){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileSize(file.getSize());
        //文件名
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            throw new CustomizeException("上传文件名为空");
        }
        fileInfo.setFileName(fileName);
        //文件后缀名-类型
        String suffixName = getFileType(file);
        fileInfo.setFileType(suffixName);
        log.info("上传文件：" + fileName + " 文件后缀：" + suffixName);
        try {
            //新文件名
            String name = UUID.randomUUID() +"."+ suffixName;
            fileInfo.setSaveName(name);
            String filePath = "/"+suffixName+"/" + name;
            fileInfo.setFilePath(filePath);
            File dest = new File(new File(Constants.FILE_SAVE_PATH).getAbsolutePath().concat(filePath));
            if (!dest.getParentFile().exists()) {
                if(!dest.getParentFile().mkdirs()){
                    throw new CustomizeException(ResultCode.ERROR,"创建文件路径失败");
                }
            }
            file.transferTo(dest);
            //文件上传成功，返回文件信息
            log.info("文件上传成功:{}",fileInfo);
            return fileInfo;
        }catch (IllegalStateException | IOException | DataIntegrityViolationException | AssertionError e){
            e.fillInStackTrace();
            log.error(e.getMessage());
            throw new CustomizeException(ResultCode.ERROR,"创建文件路径失败");
        }
    }

    /**
     * 获取文件类型
     * @param file file
     * @return java.lang.String
     */
    private static String getFileType(MultipartFile file){
        String suffixName;//= fileName.substring(fileName.lastIndexOf(".")+1);
        String contentType = file.getContentType();
        if(contentType != null){
            int i = contentType.indexOf("/")+1;
            if(i > 0){
                return contentType.substring(i);
            }
        }
        return file.getName().substring(file.getName().lastIndexOf(".")+1);
    }

    /**
     * 下载文件
     * @param id 文件id
     * @return 文件信息
     */
    public CyBlogsFile fileDownLoad(Integer id) {
        CyBlogsFile cyBlogsFile=cyBlogsFileMapper.selectFile(id);
        if(cyBlogsFile==null){
            throw new CustomizeException(ResultCode.RESULT_DATA_NONE);
        }
        String filePath=new File(cyBlogsFile.getFilePath()).getAbsolutePath();
        filePath+="/"+cyBlogsFile.getSaveName();
        cyBlogsFile.setFileUrl(filePath);
        return cyBlogsFile;
    }


}
