package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件基本属性
 *
 * @author copying
 * @date 2020-08-15 10:59:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cy_blogs_file")
public class CyBlogsFile implements Serializable {

    private static final long serialVersionUID = -559132322914896161L;
    private static final long UNIT_SIZE = 1024;

    @ApiModelProperty("主键")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;

    @ApiModelProperty("上传文件名")
    @Size(max = 255, message = "文件名长度不能超过255个字符")
    private String fileName;

    @ApiModelProperty("保存文件名-生成")
    private String saveName;

    @ApiModelProperty("文件地址")
    private String filePath;

    @ApiModelProperty("文件上传主机ip")
    private String uploadIp;

    @ApiModelProperty("文件字节")
    @NotNull(message = "文件字节不可为空")
    private Long fileSize;

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        long fsm = fileSize / UNIT_SIZE;
        if (fsm < UNIT_SIZE) {
            this.fileSizeM = (fsm + "K");
        } else {
            fsm = fsm / UNIT_SIZE;
            if (fsm < UNIT_SIZE) {
                this.fileSizeM = (fsm + "M");
            } else {
                fsm = fsm / UNIT_SIZE;
                this.fileSizeM = (fsm + "G");
            }
        }
    }

    @ApiModelProperty("文件大小")
    private String fileSizeM;

    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("下载文件地址")
    private String fileUrl;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
