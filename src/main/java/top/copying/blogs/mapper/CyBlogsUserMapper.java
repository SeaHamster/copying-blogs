package top.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.copying.blogs.model.entity.CyBlogsUser;


/**
 * @author copying
 * @date 2020-08-12
 */
@Mapper
public interface CyBlogsUserMapper extends BaseMapper<CyBlogsUser> {

    CyBlogsUser insertUser(CyBlogsUser cyBlogsUser);

}
