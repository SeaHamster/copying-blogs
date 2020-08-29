package top.copying.blogs.photo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.properties.ReadGlobalConfigProperties;
import top.copying.blogs.exception.CustomizeException;
import top.copying.blogs.photo.service.PhotoProcessService;
import top.copying.blogs.util.exception.ResponseCode;
import top.copying.blogs.util.photo.PhotoUtil;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author copying
 * @date 2020-08-18 14:27:56
 */
@Service
public class PhotoProcessImpl implements PhotoProcessService {
    @Resource
    private PhotoUtil photoUtil;

    @Resource
    private ReadGlobalConfigProperties readGlobalConfigProperties;
    /** 图片上传地址*/
    private static final String FILE_PATH="upLoad-photo";
    /** 默认生成的图片大小150K */
    private  static final  Integer DEFAULT_TO_SIZE=150*1024;

    @Override
    public String upLoadPhoto(MultipartFile file,String ip) {
        if(file.isEmpty()){
            throw new CustomizeException(ResponseCode.UP_LOAD_FILE);
        }
        return photoUtil.upLoadPhoto(file,ip, readGlobalConfigProperties.getFilePath().get(FILE_PATH));
    }

    @Override
    public String getFinishedPhoto(String fileSaveName, int fileClass,int toSize) {
        //获取原文件
        File artworkMaster =photoUtil.getPhotoFile(fileSaveName);
        //将源文件转换为，需要的图片类型
        String filePath=null;
        switch (fileClass){
            case 1:
                //将图片转换为1寸图片
                filePath=photoUtil.convertFileOne(artworkMaster);
                break;
            case 2:
                //将图片转换为2寸图片
                filePath=photoUtil.convertFileTwo(artworkMaster);
                break;
            case 3:
                //小二寸
                filePath=photoUtil.convertFileSmallTwo(artworkMaster);
                break;
            case 4:
                //转换为指定大小?K
                if(toSize==0){
                    toSize=DEFAULT_TO_SIZE;
                }
                filePath=photoUtil.convertFileFreeSize(artworkMaster,toSize);
                break;
            default:
                break;
        }
        return filePath;
    }
}
