package top.copying.blogs.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件基本属性
 * @author copying
 * @date 2020-08-15 10:59:14
 */
@Data
public class FileDto implements Serializable {
    private static final long serialVersionUID = -559132322914896161L;

    @ApiModelProperty("文件地址")
    String filePath;

    @ApiModelProperty("文件名")
    String fileName;

    @ApiModelProperty("上传文件名")
    String upFileName;

    @ApiModelProperty("文件大小")
    Long fileSize;

    @ApiModelProperty("文件类型")
    String fileType;

    @ApiModelProperty("文件的唯一标志")
    Integer fileHashCode;
}
