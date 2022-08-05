package com.copying.blogs.util;

import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.mapper.CyBlogsFileMapper;
import com.copying.blogs.model.entity.CyBlogsFile;
import com.copying.blogs.model.entity.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
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
    public String fileUpLoad(MultipartFile file,String ip, String filePath){
        //文件名
        String fileName = file.getOriginalFilename();
        //文件后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
        log.info("上传文件：" + fileName + " 文件后缀：" + suffixName);
        try {
            //新文件名
            String name = UUID.randomUUID() +"."+ suffixName;
            File dest = new File(new File(filePath).getAbsolutePath()+"/" + name);
            if (!dest.getParentFile().exists()) {
                boolean isMk=dest.getParentFile().mkdir();
                if(!isMk){
                    throw new CustomizeException(ResultCode.ERROR,"创建文件路径失败");
                }
            }
            file.transferTo(dest);
            //文件上传成功，将信息存入数据库
            CyBlogsFile cyBlogsFile =new CyBlogsFile();
            cyBlogsFile.setFileName(fileName);
            cyBlogsFile.setSaveName(name);
            cyBlogsFile.setFilePath(filePath);
            cyBlogsFile.setFileSize(dest.length());
            cyBlogsFile.setFileType(suffixName);
            cyBlogsFile.setUploadIp(ip);

            if(cyBlogsFileMapper.insertFile(cyBlogsFile)){
                return cyBlogsFile.getSaveName();
            }
        }catch (IllegalStateException | IOException | DataIntegrityViolationException | AssertionError e){
            throw new CustomizeException(ResultCode.ERROR,"创建文件路径失败");
        }

        return null;
    }

    /**
     * 下载文件
     * @param id 文件id
     * @return 文件信息
     */
    public CyBlogsFile fileDownLoad(Integer id) {
        CyBlogsFile cyBlogsFile=cyBlogsFileMapper.selectFile(id);
        if(cyBlogsFile==null){
            throw new CustomizeException(ResultCode.RESULE_DATA_NONE);
        }
        String filePath=new File(cyBlogsFile.getFilePath()).getAbsolutePath();
        filePath+="/"+cyBlogsFile.getSaveName();
        cyBlogsFile.setFileUrl(filePath);
        return cyBlogsFile;
    }


}
