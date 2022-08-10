package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 * @author fzz
 * @date 2022/8/5
 */
@Data
@Builder
@TableName("cy_blogs_user")
public class CyBlogsUser implements Serializable {

    private static final long serialVersionUID = -8434889608264248218L;
    /**   id */
    @TableId(value = "user_id",type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long userId;

    /**   用户昵称 */
    @NotBlank(message = "用户昵称不能为空")
    @Size(max = 10, message = "用户昵称长度不能超过10个字符")
    @ApiModelProperty("用户昵称")
    private String name;

    /**   用户账号 */
    @NotBlank(message = "用户账号不能为空")
    @Size(max = 20, message = "用户账号长度不能超过20个字符")
    @ApiModelProperty("用户账号")
    private String username;

    /**   用户密码 */
    @ApiModelProperty("密码")
    @Size(max = 30, message = "用户密码长度不能超过20个字符")
    private String password;

    /**   邮件 */
    @ApiModelProperty("邮件")
    private String email;

    /**   简介 */
    @ApiModelProperty("简介")
    private String about;

    /**   地区 */
    @ApiModelProperty("地区")
    private String location;

    /**   微信 */
    @ApiModelProperty("微信")
    private String wechat;

    /**   QQ */
    @ApiModelProperty("QQ")
    private String qq;

    /**   头像 */
    @ApiModelProperty("头像")
    private String avatar;

    /**   用户角色ID */
    @NotNull(message = "用户角色ID不能为空")
    @ApiModelProperty("用户角色ID")
    private Long roleId;

    /**   注册时间 */
    @ApiModelProperty("注册时间")
    private Date createTime;

    /**   变更时间 */
    @ApiModelProperty("变更时间")
    private Date updateTime;

    /**   OAuth2用户ID */
    @ApiModelProperty("OAuth2用户ID")
    private Integer oauthType;

    /**   OAuth2用户token */
    @ApiModelProperty("OAuth2用户token")
    private Long oauthUsId;

    /**   OAuth2类型 */
    @ApiModelProperty("OAuth2类型")
    private String oauthToken;


    /**
     * 权限列表
     */
    @TableField(exist = false)
    @ApiModelProperty("权限列表")
    private List<String> permissionList;

    /**
     * 角色表
     */
    @TableField(exist = false)
    @ApiModelProperty("角色表")
    private SysRole role;
}
