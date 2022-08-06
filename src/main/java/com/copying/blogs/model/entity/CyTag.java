package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * CyBlogsTag :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Data
@TableName("cy_tag")
public class CyTag implements Serializable {
    private static final long serialVersionUID = 7462284557028140082L;

    @ApiModelProperty("id")
    @TableId(value = "tag_id",type = IdType.AUTO)
    private Long tagId;

    @ApiModelProperty("标签名称")
    @NotBlank(message = "标签名称不能为空")
    private String name;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
    //------------------------------------------------------------------------------------------------------------------
//    @TableField(exist=false)
//    private List<CyBlog> blogs;

    @TableField(exist=false)
    private Integer blogsNum;
}
