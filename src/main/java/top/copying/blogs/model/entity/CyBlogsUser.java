package top.copying.blogs.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;

/**
 * @author copying
 * @date 2020-08-11
 * 用户实体类
 */
@Data
public class CyBlogsUser {

    @ApiModelProperty("用户标识")
    private Integer userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("密码")
    private String passWord;
    @ApiModelProperty("创建时间")
    private Instant createTime;
    @ApiModelProperty("更新时间")
    private Instant updateTime;
    @ApiModelProperty("用户权限")
    private Integer authority;
    @ApiModelProperty("身份证id")
    private Integer idNumber;
    @ApiModelProperty("身份证姓名")
    private String idName;
    @ApiModelProperty("用户状态")
    private Integer state;

}
