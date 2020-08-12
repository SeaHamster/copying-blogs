package top.copying.blogs.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import top.copying.blogs.mapper.CyBlogsUserMapper;
import top.copying.blogs.model.entity.CyBlogsUser;
import top.copying.blogs.org.service.CyBlogsUserService;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author copying
 * @date 2020-08-11
 * 用户管理具体实现
 */
@Component
public class CyBlogsUserImpl implements CyBlogsUserService {

    @Resource
    private CyBlogsUserMapper cyBlogsUserMapper;
    @Override
    public Boolean isLogin(String userName, String passWord) {
        QueryWrapper<CyBlogsUser> cyBlogsUserQueryWrapper=new QueryWrapper<>();
        cyBlogsUserQueryWrapper.lambda().eq(CyBlogsUser::getUserName,userName);

        List<CyBlogsUser> cyBlogsUsers= cyBlogsUserMapper.selectList(cyBlogsUserQueryWrapper);
        return !cyBlogsUsers.isEmpty();
    }
}
