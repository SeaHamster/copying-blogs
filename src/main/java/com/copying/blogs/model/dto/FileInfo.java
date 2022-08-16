package com.copying.blogs.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fzz
 * @date 2022/8/16
 */
@Data
public class FileInfo {

    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 存储文件名
     */
    @ApiModelProperty("存储文件名")
    private String saveName;

    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;

    /**
     * 实际存储地址的相对文件地址
     * 例：实际地址 /home/file/file1.jpg
     *  filePath = /file/file1.jpg
     */
    @ApiModelProperty("文件地址")
    private String filePath;

    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Long fileSize;


}
