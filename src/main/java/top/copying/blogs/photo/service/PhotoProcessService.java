package top.copying.blogs.photo.service;


import org.springframework.web.multipart.MultipartFile;
import top.copying.blogs.model.entity.CyBlogsFile;
import top.copying.blogs.model.entity.CyBlogsPhotoFile;

/**
 * @author copying
 * @date 2020-08-14
 */
public interface PhotoProcessService {
    /**
     * 上传图片文件
     * @param file 目标图片
     * @param ip 上传文件的ip
     * @return 图片新的uuid名称
     */
    String upLoadPhoto(MultipartFile file,String ip);

    /**
     * 按需求获取图片
     * @param fileId 原图片id
     * @param fileClass 需要的图片格式
     * @param toSize 转换后图片的大小
     * @return 完成后的图片地址
     */
    String getFinishedPhoto(String fileId, int fileClass,int toSize);
}
