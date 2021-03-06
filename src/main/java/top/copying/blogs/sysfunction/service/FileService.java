package top.copying.blogs.sysfunction.service;

import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.model.entity.CyBlogsFile;

import java.util.List;

/**
 * @author copying
 * @date 2020-08-17 09:32:20
 */
public interface FileService {
    /**
     * 上传文件
     * @param file 要上传的文件
     * @param ip 上传文件的地址
     * @return 文件信息
     */
    String upLoadFile(MultipartFile file,String ip);

    /**
     * 下载文件
     * @param id 文件id
     * @return 文件信息
     */
    CyBlogsFile downLoadFile(Integer id);
}
