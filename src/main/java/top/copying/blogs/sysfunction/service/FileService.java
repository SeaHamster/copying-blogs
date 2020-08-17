package top.copying.blogs.sysfunction.service;

import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.model.entity.CyBlogsFile;

/**
 * @author copying
 * @date 2020-08-17 09:32:20
 */
public interface FileService {
    /**
     * 上传文件
     * @param file 要上传的文件
     * @return 文件信息
     */
    CyBlogsFile upLoadFile(MultipartFile file);
}
