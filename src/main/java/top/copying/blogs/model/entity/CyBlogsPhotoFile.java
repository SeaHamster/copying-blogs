package top.copying.blogs.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 图片存储
 * @author copying
 * @date 2020-08-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CyBlogsPhotoFile extends CyBlogsFile {

    private static final long serialVersionUID = -983584757776619531L;
    @ApiModelProperty("图片格式：一寸等")
    private Integer fileClass;
    @ApiModelProperty("源图片ID")
    private Integer fileId;

}
