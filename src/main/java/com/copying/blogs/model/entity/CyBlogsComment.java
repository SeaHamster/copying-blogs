package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * CyBlogsComment : 评论表
 * Date: 2022/8/6
 *
 * @author copying
 */
@Data
@Builder
@TableName("cy_blogs_comment")
public class CyBlogsComment implements Serializable {

    private static final long serialVersionUID = 6728300087630825436L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    private String name;
    private String email;
    private String content;
    private Boolean isAdmin;
    private Boolean isDelete;
    private Long blogId;
    private Long parentId;
    private String ipAddress;
    private Date createTime;
    private Date updateTime;

    //------------------------------------------------------------------------------------------------------------------
    @TableField(exist = false)
    private CyBlog blog;

    @TableField(exist = false)
    private List<CyBlogsComment> childList;

    @TableField(exist = false)
    private CyBlogsComment parent;
}
