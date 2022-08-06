package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * CyBlog :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Data
@TableName("cy_blog")
public class CyBlog {

    @TableId(value = "blog_id",type = IdType.AUTO)
    private Long blogId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotBlank(message = "简介不能为空")
    private String outline;

    private String backgroundImage;

    private Boolean recommend;

    private Boolean commentable;

    private Boolean published;

    private Integer views;

    private Long typeId;

    private Date createTime;

    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------

    @TableField(exist=false)
    private Integer commentNum;

    @TableField(exist=false)
    private List<CyTag> tags;

    @TableField(exist=false)
    private List<CyBlogsComment> comments;

    @TableField(exist=false)
    private CyBlogsType type;

    @TableField(exist=false)
    private Long[] tagIds;

}
