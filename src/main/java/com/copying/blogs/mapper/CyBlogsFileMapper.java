package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.entity.CyBlogsFile;

/**
 * @author copying
 * @date 2020-08-17 13:35:25
 */
public interface CyBlogsFileMapper extends BaseMapper<CyBlogsFile> {

    /**
     * 文件上传成功后，将信息写入数据库
     * @param cyBlogsFile 文件信息
     * @return 大于0插入成功
     */
    boolean insertFile(CyBlogsFile cyBlogsFile);

    /**
     * 根据文件id查询文件信息
     * @param id 主键id
     * @return CyBlogsFile文件信息
     */
    CyBlogsFile selectFile(Integer id);

    /**
     * 根据生成文件名获取信息
     * @param fileSaveName fileSaveName
     * @return com.copying.blogs.model.entity.CyBlogsFile
     */
    CyBlogsFile selectFileSaveName(String fileSaveName);
}
