package com.copying.blogs.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

//为NULL的字段不返回为（JSON）
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class TimeLineBlog implements Serializable {
    private static final long serialVersionUID = 3440678780749786203L;

    private Long blogId;
    private String title;
    private String date;
    private String month;

}
