package com.copying.blogs.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 图片存储
 * @author copying
 * @date 2020-08-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyBlogsPhotoFile extends CyBlogsFile {

    private static final long serialVersionUID = -983584757776619531L;
    @ApiModelProperty("图片格式：一寸等")
    private Integer fileClass;
    @ApiModelProperty("源图片ID")
    private Integer fileId;

}
