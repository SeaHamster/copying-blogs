package top.copying.blogs.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件上传
 * @author copying
 * @date 2020-08-15
 */
public class FileUtil {

    /**
     * 文件上传
     * @return 文件地址
     */
    private String fileUpLoad(MultipartFile file,String filePath){
        //文件名
        String fileName=file.getOriginalFilename();
        //文件后缀名
        assert fileName != null;
        String suffixName=fileName.substring(fileName.indexOf("."));
        //新文件名
        fileName= UUID.randomUUID()+suffixName;
        File dest=new File(filePath+fileName);



        return null;
    }
}
