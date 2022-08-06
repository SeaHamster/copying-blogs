package com.copying.blogs.aspect;

import com.copying.blogs.model.entity.CyRequestLog;
import com.copying.blogs.service.CyRequestLogService;
import com.copying.blogs.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

// 使用@Aspect注解声明一个切面
@Aspect
@Component
@Slf4j
public class MyLogAspect {

    @Resource
    private CyRequestLogService cyRequestLogService;

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Before("execution(public * com.copying.blogs.controller..*(..)) && @annotation(myLog)")
    public void doBefore(JoinPoint joinPoint,MyLog myLog) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null){
            log.error("MyLogAspect:doBefore 获取 ServletRequestAttributes 失败！！！");
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        String ipAddress = IpUtil.getIpAddress(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsStr=Arrays.toString(args);
        if(argsStr.length()>=255){argsStr=argsStr.substring(0,245)+"......";}
        cyRequestLogService.save(CyRequestLog.builder().url(url).ipAddress(ipAddress).classMethod(classMethod).args(argsStr).build());
    }




}
