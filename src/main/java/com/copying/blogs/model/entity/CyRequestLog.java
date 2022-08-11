package com.copying.blogs.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * CyRequestLog :
 * Date: 2022/8/6
 *
 * @author copying
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("cy_request_log")
public class CyRequestLog implements Serializable {
    private static final long serialVersionUID = 2510582190944194083L;

    @TableId(value = "log_id",type = IdType.AUTO)
    private Long logId;
    private String url;
    private String ipAddress;
    private String classMethod;
    private String args;
    private Date createTime;
}
