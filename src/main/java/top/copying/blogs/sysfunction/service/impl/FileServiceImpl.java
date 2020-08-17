package top.copying.blogs.sysfunction.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.config.ReadGlobalConfig;
import top.copying.blogs.model.dto.CyBlogsFileDto;
import top.copying.blogs.sysfunction.service.FileService;
import top.copying.blogs.util.FileUtil;
import top.copying.blogs.util.exception.ResponseCode;

import javax.annotation.Resource;

/**
 * @author copying
 * @date 2020-08-17 09:34:15
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileUtil fileUtil;
    @Resource
    private ReadGlobalConfig readGlobalConfig;

    private static final String FILE_PATH="public-file";

    @Override
    public CyBlogsFileDto upLoadFile(MultipartFile file) {
        if(file.isEmpty()){
            throw new ClassCastException(ResponseCode.COMMON_MISS_PARAMETER);
        }
        return fileUtil.fileUpLoad(file,readGlobalConfig.getFilePath().get(FILE_PATH));
    }
}