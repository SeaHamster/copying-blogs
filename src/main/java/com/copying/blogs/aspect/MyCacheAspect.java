package com.copying.blogs.aspect;


import com.copying.blogs.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 缓存redis切面
 */
@Slf4j
@Aspect
@Component
public class MyCacheAspect {
    @Resource
    private RedisUtil redisUtil;

    private String createCacheKey(ProceedingJoinPoint jp) {
        Signature signature = jp.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        StringBuilder sbKey = new StringBuilder();
        sbKey.append(className);
        sbKey.append(".");
        sbKey.append(methodName);
        //方法参数值
        Object[] args = jp.getArgs();
        for (Object object : args) {
            sbKey.append("-");
            sbKey.append(object);
        }
        return sbKey.toString();
    }


    //@Around("@annotation(myCache)")
    @Order(1)
    @Around("execution(public * com.copying.blogs.service.impl..*(..)) && @annotation(myCache)")
    public Object around(ProceedingJoinPoint jp, MyCache myCache) {
//        long startTime = System.currentTimeMillis();
        //生成Redis中的key
        String key = createCacheKey(jp);
        //如果有缓存直接返回，没有正常执行并写入缓存
        try {
            if (redisUtil.hasKey(key)) {
                return redisUtil.get(key);
            } else {
                Object result = jp.proceed(jp.getArgs());
                boolean set = redisUtil.set(key, result, myCache.overTime());
                if(!set){
                    log.error("MyCacheAspect:around:set 操作失败");
                }
                return result;
            }
        } catch (Throwable t) {
            log.error(t.toString());
            return null;
        }
//        finally {
////            log.info("{}  方法执行时间： {}",jp.getSignature().getName(),System.currentTimeMillis()-startTime);
//        }
    }

}
