package com.copying.blogs.security;

import com.alibaba.fastjson.JSON;
import com.copying.blogs.model.entity.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fzz
 * @date 2022/8/5
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("用户访问资源没有携带正确的token,msg:{}", e.getMessage());
        final String json = JSON.toJSONString(new JsonResult(30001, "用户访问资源没有携带正确的token", e.getMessage()));
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(json);

    }
}
