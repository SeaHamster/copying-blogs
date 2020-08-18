package top.copying.blogs.photo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.config.ReadGlobalConfig;
import top.copying.blogs.exception.CustomizeException;
import top.copying.blogs.model.entity.CyBlogsFile;
import top.copying.blogs.model.entity.CyBlogsPhotoFile;
import top.copying.blogs.photo.service.PhotoProcessService;
import top.copying.blogs.util.exception.ResponseCode;
import top.copying.blogs.util.photo.PhotoUtil;

import javax.annotation.Resource;

/**
 * @author copying
 * @date 2020-08-18 14:27:56
 */
@Service
public class PhotoProcessImpl implements PhotoProcessService {
    @Resource
    private PhotoUtil photoUtil;
    @Resource
    private ReadGlobalConfig readGlobalConfig;
    /** 图片上传地址*/
    private static final String FILE_PATH="upLoad-photo";

    @Override
    public String upLoadPhoto(MultipartFile file,String ip) {
        if(file.isEmpty()){
            throw new CustomizeException(ResponseCode.UP_LOAD_FILE);
        }
        return photoUtil.upLoadPhoto(file,ip,readGlobalConfig.getFilePath().get(FILE_PATH));
    }

    @Override
    public CyBlogsPhotoFile getFinishedPhoto(String fileId, int fileClass) {
        return null;
    }
}
