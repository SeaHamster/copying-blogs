package com.copying.blogs.security;

import com.alibaba.fastjson.JSON;
import com.copying.blogs.model.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fzz
 * @date 2022/8/5
 */
@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.error("用户访问没有授权资源,msg:{}", e.getMessage());
        final String json = JSON.toJSONString(new JsonResult<>(30002, "用户访问没有授权资源", e.getMessage()));
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(json);
    }
}
