package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 *id,名称,职业,描述,标志,网址
 * */
@Data
@Builder
@TableName("cy_blogs_friend")
public class CyBlogsFriend implements Serializable {
    private static final long serialVersionUID = -6283781496434738647L;


    @TableId(value = "fr_id",type = IdType.AUTO)
    private Long frId;
    @NotBlank(message = "Name不能为空")
    private String name;
    @NotBlank(message = "简介不能为空")
    private String description;
    @NotBlank(message = "网站地址不能为空")
    private String website;
    private Integer flag;
    private Date createTime;
    private Date updateTime;
}
