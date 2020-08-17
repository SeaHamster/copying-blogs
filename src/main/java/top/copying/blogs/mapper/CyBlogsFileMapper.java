package top.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.copying.blogs.model.dto.CyBlogsFileDto;

/**
 * @author copying
 * @date 2020-08-17 13:35:25
 */
public interface CyBlogsFileMapper extends BaseMapper<CyBlogsFileDto> {

    /**
     * 文件上传成功后，将信息写入数据库
     * @param cyBlogsFileDto 文件信息
     * @return 大于0插入成功
     */
    int insertFile(@Param("cyBlogsFileDto")CyBlogsFileDto cyBlogsFileDto);


}
