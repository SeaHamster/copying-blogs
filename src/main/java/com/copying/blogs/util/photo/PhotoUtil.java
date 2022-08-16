package com.copying.blogs.util.photo;

import com.copying.blogs.config.ReadPhotoTypeConfig;
import com.copying.blogs.mapper.CyBlogsFileMapper;
import com.copying.blogs.model.entity.CyBlogsFile;
import com.copying.blogs.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


/**
 * 图片处理工具具体实现
 * @author copying
 * @date 2020-08-15
 */
@Component
@Slf4j
@SuppressWarnings("unused")
public class PhotoUtil {
    /**  日志 */
    @Resource
    private CyBlogsFileMapper cyBlogsFileMapper;
    @Resource
    private FileUtil fileUtil;

    @Resource
    private ReadPhotoTypeConfig readPhotoTypeConfig;
    //** 图片类型*/
    //private static final String PHOTO_TYPE="photo-type";

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

    /**
     * 获取图片
     * @param fileSaveName 存储文件的uuid
     * @return 图片
     */
    public File getPhotoFile(String fileSaveName) {
        //获取图片信息，确定位置，导入图片
        CyBlogsFile cyBlogsFile=cyBlogsFileMapper.selectFileSaveName(fileSaveName);
        //组装文件地址
        String filePath=new File(cyBlogsFile.getFilePath()).getAbsolutePath()+"/";
        filePath+=cyBlogsFile.getSaveName();
        return new File(filePath);
    }

    /**
     * 将图片文件转变为一寸图
     * @param artworkMaster 原图
     * @return 新图地址
     */
    public String convertFileOne(File artworkMaster) {

        return null;
    }

    /**
     * 将图片文件转变为二寸图
     * @param artworkMaster 原图
     * @return 新图地址
     */
    public String convertFileTwo(File artworkMaster) {
        return null;
    }
    /**
     * 将图片文件转变为小二寸图
     * @param artworkMaster 原图
     * @return 新图地址
     */
    public String convertFileSmallTwo(File artworkMaster) {
        return null;
    }

    /**
     * 转换文件自由大小
     * @param artworkMaster artworkMaster
     * @param toSize toSize
     * @return String
     */
    public String convertFileFreeSize(File artworkMaster, int toSize) {
        return null;
    }
}
