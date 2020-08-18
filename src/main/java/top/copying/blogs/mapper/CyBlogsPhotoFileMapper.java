package top.copying.blogs.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.copying.blogs.model.entity.CyBlogsPhotoFile;

/**
 * @author copying
 * @date 2020-08-18 14:46:22
 */
@Mapper
public interface CyBlogsPhotoFileMapper {
    /**
     * 将上传成功的图片信息存入数据库
     * @param cyBlogsPhotoFile 图片信息
     * @return 是否成功
     */
    boolean insertPhotoFile(CyBlogsPhotoFile cyBlogsPhotoFile);
}
