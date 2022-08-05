package com.copying.blogs.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.copying.blogs.model.entity.CyBlogsRole;
import com.copying.blogs.model.entity.CyBlogsUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fzz
 * @date 2022/8/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CyBlogsUserDto extends CyBlogsUser implements UserDetails {

    private static final long serialVersionUID = 1078739085639076530L;
    /**
     * 权限列表
     */
    @ApiModelProperty("权限列表")
    private List<String> permissionList;

    /**
     * 角色表
     */
    @TableField(exist=false)
    private CyBlogsRole role;


    private Set<GrantedAuthority> authorities = new HashSet<>();

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
