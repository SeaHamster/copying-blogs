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
     * @return 图片信息
     */
    CyBlogsFile upLoadPhoto(MultipartFile file);

    /**
     * 按需求获取图片
     * @param fileId 原图片id
     * @param fileClass 需要的图片格式
     * @return 完成后的图片信息
     */
    CyBlogsPhotoFile getFinishedPhoto(String fileId, int fileClass);
}
