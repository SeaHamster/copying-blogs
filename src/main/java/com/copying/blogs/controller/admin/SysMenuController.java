package com.copying.blogs.controller.admin;

import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.dto.TreeSelect;
import com.copying.blogs.model.entity.SysMenu;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.SysMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 菜单信息
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public JsonResult<?> roleMenuTreeSelect(@PathVariable("roleId") Long roleId, CyBlogsUserDto userDto) {
        if (userDto == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = sysMenuService.selectMenuList(userDto.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", sysMenuService.selectMenuListByRoleId(roleId));
        map.put("menus", sysMenuService.buildMenuTreeSelect(menus));
        return Result.success(map, ResultCode.SUCCESS);
    }


    /**
     * 加载菜单列表树
     */
    @GetMapping(value = "/treeselect")
    public JsonResult<?> roleMenuTreeSelect(CyBlogsUserDto userDto) {
        if (userDto == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = sysMenuService.selectMenuList(userDto.getUserId());
        List<TreeSelect> treeSelects = sysMenuService.buildMenuTreeSelect(menus);
        return Result.success(treeSelects, ResultCode.SUCCESS);
    }


}