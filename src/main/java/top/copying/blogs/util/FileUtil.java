package top.copying.blogs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.exception.CustomizeException;
import top.copying.blogs.mapper.CyBlogsFileMapper;
import top.copying.blogs.model.entity.CyBlogsFile;
import top.copying.blogs.sysfunction.controller.FileController;
import top.copying.blogs.util.exception.ResponseCode;

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
public class FileUtil {

    private static final Logger log= LoggerFactory.getLogger(FileController.class);
    @Resource
    private CyBlogsFileMapper cyBlogsFileMapper;

    /**
     * 文件上传
     * @return 文件地址
     */
    public CyBlogsFile fileUpLoad(MultipartFile file, String filePath){
        //文件名
        String fileName = file.getOriginalFilename();
        //文件后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.indexOf("."));
        log.info("上传文件：" + fileName + " 文件后缀：" + suffixName);

        try {

            //新文件名
            String name = UUID.randomUUID() + suffixName;
            File dest = new File(new File(filePath).getAbsolutePath()+"/" + name);

            if (!dest.getParentFile().exists()) {
                boolean isMk=dest.getParentFile().mkdir();
                if(!isMk){
                    throw new CustomizeException(ResponseCode.COMMON_NOT_ALLOW,"创建文件路径失败","Failed to create file path");
                }
            }
            file.transferTo(dest);
            //文件上传成功，将信息存入数据库
            CyBlogsFile cyBlogsFile =new CyBlogsFile();
            cyBlogsFile.setFileName(fileName);
            cyBlogsFile.setName(name);
            cyBlogsFile.setFilePath(filePath+name);
            cyBlogsFile.setFileHashCode(dest.hashCode());
            cyBlogsFile.setFileSize(dest.length());
            cyBlogsFile.setFileType(suffixName);


            int insertFile=cyBlogsFileMapper.insertFile(cyBlogsFile);
            if(insertFile>0){
                return cyBlogsFile;
            }
        }catch (IllegalStateException | IOException | AssertionError  e){
            throw new CustomizeException(ResponseCode.UP_LOAD_FILE);
        }

        return null;
    }
}
