package com.copying.blogs.security;

import com.copying.blogs.model.dto.CyBlogsUserDto;
import com.copying.blogs.model.entity.CyBlogsUser;
import com.copying.blogs.service.CyBlogsUserService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * @author fzz
 * @date 2022/8/5
 */
public class PrincipalUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private CyBlogsUserService cyUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(CyBlogsUserDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        final Long userId = (Long) authentication.getPrincipal();
        return cyUserService.getMyUserById(userId);
    }
}
