package top.copying.blogs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.exception.CustomizeException;
import top.copying.blogs.model.dto.FileDto;
import top.copying.blogs.sysfunction.controller.FileController;
import top.copying.blogs.util.exception.ResponseCode;

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

    /**
     * 文件上传
     * @return 文件地址
     */
    public FileDto fileUpLoad(MultipartFile file, String filePath){
        FileDto fileDto = null;
        try {
            //文件名
            String upfileName = file.getOriginalFilename();
            //文件后缀名
            assert upfileName != null;
            String suffixName = upfileName.substring(upfileName.indexOf("."));
            log.info("上传文件：" + upfileName + " 文件后缀：" + suffixName);

            //新文件名
            String fileName = UUID.randomUUID() + suffixName;
            File dest = new File(new File(filePath).getAbsolutePath()+"/" + fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
            //文件上传成功，将信息存入数据库


            fileDto.setFileName(fileName);
            fileDto.setUpFileName(upfileName);
            fileDto.setFilePath(filePath+fileName);
            fileDto.setFileHashCode(dest.hashCode());
            fileDto.setFileSize(dest.length());
            fileDto.setFileType(suffixName);

        }catch (IllegalStateException | IOException illegalStateException){
            throw new CustomizeException(ResponseCode.UP_LOAD_FILE);
        }

        return fileDto;
    }
}
