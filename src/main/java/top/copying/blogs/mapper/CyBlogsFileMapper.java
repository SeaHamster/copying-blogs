package top.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.copying.blogs.model.entity.CyBlogsFile;

/**
 * @author copying
 * @date 2020-08-17 13:35:25
 */
@Mapper
public interface CyBlogsFileMapper extends BaseMapper<CyBlogsFile> {

    /**
     * 文件上传成功后，将信息写入数据库
     * @param cyBlogsFile 文件信息
     * @return 大于0插入成功
     */
    int insertFile(@Param("cyBlogsFileDto") CyBlogsFile cyBlogsFile);


}
