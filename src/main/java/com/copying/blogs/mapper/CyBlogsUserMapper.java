package com.copying.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copying.blogs.model.entity.CyBlogsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author copying
 * @date 2020-08-12
 */
@Mapper
@SuppressWarnings("unused")
public interface CyBlogsUserMapper extends BaseMapper<CyBlogsUser> {

    @Select("SELECT distinct ifNull(mu.perms,'') as perms  FROM sys_menu mu\n" +
            "LEFT JOIN sys_role_menu rm ON mu.`menu_id`=rm.`menu_id`\n" +
            "LEFT JOIN sys_user us on us.`role_id`=rm.`role_id`\n" +
            "WHERE LENGTH(perms)>=1 and us.`us_id`=#{usId}")
    List<String> getPermissionsById(Long usId);

    @Select("SELECT distinct ifNull(mu.perms,'') as perms FROM sys_menu mu WHERE LENGTH(perms)>=1")
    List<String> getPermissionsAll();

    @Select("SELECT role_key FROM sys_role ro LEFT JOIN sys_user us on us.role_id=ro.role_id WHERE us.us_id=#{usId}")
    String getRoleKeyById(Long usId);
}
