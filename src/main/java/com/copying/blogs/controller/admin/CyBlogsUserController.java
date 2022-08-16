package com.copying.blogs.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.copying.blogs.model.entity.SysRole;
import com.copying.blogs.model.result.JsonResult;
import com.copying.blogs.model.result.Result;
import com.copying.blogs.model.result.ResultCode;
import com.copying.blogs.service.CyBlogsFileService;
import com.copying.blogs.service.CyBlogsUserService;
import com.copying.blogs.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Api
@RestController
@RequestMapping("/system/user")
public class CyBlogsUserController {
    @Resource
    private CyBlogsUserService cyBlogsUserService;
    @Resource
    private SysRoleService iSysRoleService;
    @Resource
    private CyBlogsFileService cyBlogsFileService;


    /**
     * 修改用户
     */
    @PreAuthorize("hasAuthority('system:user:edit')")
    @PostMapping("/update")
    public JsonResult<?> update(@Validated @RequestBody CyBlogsUser user) {
        if (Objects.isNull(user.getUserId())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        if (cyBlogsUserService.count(new LambdaQueryWrapper<CyBlogsUser>().ne(CyBlogsUser::getUserId, user.getUserId()).eq(CyBlogsUser::getUsername, user.getUsername())) > 0) {
            return Result.fail(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }
        boolean bool = cyBlogsUserService.updateById(user);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 新增用户
     */
    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping("/add")
    public JsonResult<?> add(@Validated @RequestBody CyBlogsUser user) {
        if (cyBlogsUserService.count(new LambdaQueryWrapper<CyBlogsUser>().eq(CyBlogsUser::getUsername, user.getUsername())) > 0) {
            return Result.fail(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }

        boolean bool = cyBlogsUserService.save(user);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/{idNum}")
    public JsonResult<?> getById(@PathVariable("idNum") Long userId) {
        Map<String, Object> map = new HashMap<>();
        CyBlogsUser userInfo = cyBlogsUserService.getById(userId);
        List<SysRole> roles = iSysRoleService.lambdaQuery().select(SysRole::getRoleId, SysRole::getRoleName,
                SysRole::getRoleSort, SysRole::getStatus).list();
        map.put("userInfo", userInfo);
        map.put("roles", roles);
        return Result.success(map, ResultCode.SUCCESS);
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/list")
    public JsonResult<?> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "userName", required = false) String userName) {
        PageInfo<CyBlogsUser> userPage = cyBlogsUserService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<CyBlogsUser>().like(Strings.isNotBlank(userName), CyBlogsUser::getUsername, userName));
        return Result.success(userPage, ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('system:user:remove')")
    @GetMapping("/delete/{userIds}")
    public JsonResult<?> remove(@PathVariable Long[] userIds) {
        for (Long id : userIds) {
            if (cyBlogsUserService.isAdmin(id)) {
                return Result.fail(ResultCode.DATA_IS_WRONG);
            }
        }
        boolean bool = cyBlogsUserService.removeByIds(Arrays.asList(userIds));
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 重置密码
     */
    @PreAuthorize("hasAuthority('system:user:resetPwd')")
    @PostMapping("/resetPwd")
    public JsonResult<?> resetPwd(@RequestBody CyBlogsUser user) {
        if (StringUtils.isBlank(user.getPassword()) || Objects.isNull(user.getUserId())) {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = cyBlogsUserService.updateById(CyBlogsUser.builder().password(user.getPassword())
                .userId(user.getUserId()).build());
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 个人信息页
     */
    @GetMapping("/profile")
    public JsonResult<?> profile(CyBlogsUserDto userDto) {
        userDto.setPassword(null);
        Map<String, Object> map = new HashMap<>();
        map.put("user", userDto);
        map.put("roleName", userDto.getRole().getRoleName());
        return Result.success(map, ResultCode.SUCCESS);
    }

    /**
     * 个人信息页修改
     */
    @PostMapping("/profile/update")
    public JsonResult<?> profileUpdate(@Validated @RequestBody CyBlogsUser user, CyBlogsUserDto userDto) {
        if (userDto == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        if (cyBlogsUserService.count(new LambdaQueryWrapper<CyBlogsUser>().ne(CyBlogsUser::getUserId, userDto.getUserId()).eq(CyBlogsUser::getUsername, user.getUsername())) > 0) {
            return Result.fail(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }
        user.setUserId(userDto.getUserId());
        user.setPassword(null);
        user.setAvatar(null);
        boolean bool = cyBlogsUserService.updateById(user);
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 个人信息修改密码
     */
    @PostMapping("/profile/updatePwd")
    public JsonResult<?> profile(CyBlogsUserDto userDto, @RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword) {
        if (userDto == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        if (!userDto.getPassword().equals(oldPassword)) {
            return Result.fail(ResultCode.PWD_AUTH_ERROR);
        }
        boolean bool = cyBlogsUserService.update(new LambdaUpdateWrapper<CyBlogsUser>().set(CyBlogsUser::getPassword, newPassword).eq(CyBlogsUser::getUserId, userDto.getUserId()));
        if (bool) {
            return Result.successNoData(ResultCode.SUCCESS);
        } else {
            return Result.fail(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 头像上传
     */
    @PostMapping("/profile/avatar")
    public JsonResult<?> create(@RequestParam("avatarfile") MultipartFile file, CyBlogsUserDto userDto) {
        if (userDto == null) {
            return Result.fail(ResultCode.Token_AUTH_ERROR);
        }
        if (!file.isEmpty()) {
            String url = cyBlogsFileService.uploadFile(file);
            cyBlogsUserService.update(new LambdaUpdateWrapper<CyBlogsUser>().set(CyBlogsUser::getAvatar, url).eq(CyBlogsUser::getUserId, userDto.getUserId()));
            return Result.success(url, ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.DATA_IS_WRONG);
    }


}
