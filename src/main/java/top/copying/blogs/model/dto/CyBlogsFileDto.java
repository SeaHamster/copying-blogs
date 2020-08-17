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
public class CyBlogsFileDto implements Serializable {
    private static final long serialVersionUID = -559132322914896161L;
    private static final long UNIT_SIZE = 1024;


    @ApiModelProperty("上传文件名")
    String fileName;

    @ApiModelProperty("文件名")
    String Name;

    @ApiModelProperty("文件地址")
    String filePath;

    @ApiModelProperty("文件的唯一标志")
    Integer fileHashCode;

    @ApiModelProperty("文件上传主机ip")
    Integer uploadIp;

    @ApiModelProperty("文件字节")
    Long fileSize;

    public void setFileSize(Long fileSize){
        this.fileSize=fileSize;
        Long fsm=fileSize/ UNIT_SIZE;
        if(fsm< UNIT_SIZE){
            this.fileSizeM=(fsm.toString()+"K");
        }else{
            fsm=fsm/ UNIT_SIZE;
            if(fsm< UNIT_SIZE){
                this.fileSizeM=(fsm.toString()+"M");
            }else {
                fsm=fsm/ UNIT_SIZE;
                this.fileSizeM=(fsm.toString()+"G");
            }
        }
    }

    @ApiModelProperty("文件大小")
    String fileSizeM;

    @ApiModelProperty("文件类型")
    String fileType;


}