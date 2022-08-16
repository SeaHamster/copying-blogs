package com.copying.blogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.copying.blogs.model.entity.CyBlogsFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fzz
 * @date 2022/8/16
 */
public interface CyBlogsFileService extends IService<CyBlogsFile> {

    /**
     * 上传文件
     * @param file file
     * @return java.lang.String
     */
    String uploadFile(MultipartFile file);
}
