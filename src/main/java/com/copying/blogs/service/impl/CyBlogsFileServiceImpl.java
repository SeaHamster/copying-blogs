package com.copying.blogs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copying.blogs.constants.Constants;
import com.copying.blogs.exception.CustomizeException;
import com.copying.blogs.mapper.CyBlogsFileMapper;
import com.copying.blogs.model.dto.FileInfo;
import com.copying.blogs.model.entity.CyBlogsFile;
import com.copying.blogs.service.CyBlogsFileService;
import com.copying.blogs.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fzz
 * @date 2022/8/16
 */
@Service
@Slf4j
public class CyBlogsFileServiceImpl extends ServiceImpl<CyBlogsFileMapper, CyBlogsFile> implements CyBlogsFileService {

    /**
     * 上传文件
     * @param file file
     * @return java.lang.String fileurl文件访问地址
     */
    @Override
    public String uploadFile(MultipartFile file) {
        //上传文件
        FileInfo fileInfo = FileUtil.fileUpLoad(file);
        // 保存文件信息
        CyBlogsFile cyBlogsFile = new CyBlogsFile();
        cyBlogsFile.setFileName(fileInfo.getFileName());
        cyBlogsFile.setSaveName(fileInfo.getSaveName());
        cyBlogsFile.setFilePath(Constants.FILE_SAVE_PATH+fileInfo.getFilePath());
        cyBlogsFile.setFileSize(fileInfo.getFileSize());
        cyBlogsFile.setFileType(fileInfo.getFileType());
        cyBlogsFile.setFileUrl(Constants.FILE_SERVER_URL+fileInfo.getFilePath());
        if(!this.save(cyBlogsFile)){
            log.error("保存文件信息失败 储存地址：{}",fileInfo.getFilePath());
            throw new CustomizeException("保存文件信息失败");
        }
        return cyBlogsFile.getFileUrl();
    }
}
