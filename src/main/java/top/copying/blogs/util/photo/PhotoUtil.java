package top.copying.blogs.util.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.config.ReadPhotoTypeConfig;
import top.copying.blogs.exception.CustomizeException;
import top.copying.blogs.mapper.CyBlogsPhotoFileMapper;
import top.copying.blogs.model.entity.CyBlogsFile;
import top.copying.blogs.sysfunction.controller.FileController;
import top.copying.blogs.util.FileUtil;
import top.copying.blogs.util.exception.ResponseCode;

import javax.annotation.Resource;
import java.util.List;


/**
 * 图片处理工具具体实现
 * @author copying
 * @date 2020-08-15
 */
@Component
public class PhotoUtil {
    /**  日志 */
    private static final Logger log= LoggerFactory.getLogger(FileController.class);
    @Resource
    private CyBlogsPhotoFileMapper cyBlogsPhotoFileMapper;
    @Resource
    private FileUtil fileUtil;

    @Resource
    private ReadPhotoTypeConfig readPhotoTypeConfig;
    //** 图片类型*/
    //private static final String PHOTO_TYPE="photo-type";

    public String upLoadPhoto(MultipartFile photo,String ip, String filePath){
        //文件名
        String fileName = photo.getOriginalFilename();
        //文件后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
        log.info("上传文件：" + fileName + " 文件后缀：" + suffixName);
        if(!isPhoto(suffixName)){
            throw new CustomizeException(ResponseCode.COMMON_NOT_ALLOW,"请上传正确的图片文件","Please upload the correct picture file");
        }

        return fileUtil.fileUpLoad(photo,ip,filePath);
    }

    /**
     * 判断是否为图片格式
     * @param fileType 文件格式
     * @return 是否为图片
     */
    private boolean isPhoto(String fileType){
        List<String> photoTypes=readPhotoTypeConfig.getPhotoType();

        for(String photoType:photoTypes){
            if(photoType.equals(fileType.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
