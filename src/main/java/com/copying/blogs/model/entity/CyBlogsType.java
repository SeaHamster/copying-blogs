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
 * CyBlogsType :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Data
@TableName("cy_blogs_type")
public class CyBlogsType implements Serializable {

    private static final long serialVersionUID = 918292312051448148L;

    @TableId(value = "type_id",type = IdType.AUTO)
    private Long typeId;

    @ApiModelProperty("类型名称")
    @NotBlank(message = "类型名称不能为空")
    private String name;

    private Date createTime;

    private Date updateTime;

    //------------------------------------------------------------------------------------------------------------------
//    @TableField(exist=false)
//    private List<CyBlog> blogs;

    @TableField(exist=false)
    private Integer blogsNum;
}
