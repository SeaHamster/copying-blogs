package com.copying.blogs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Copying
 * @date 2020-08-07
 */
@SpringBootApplication
@MapperScan("com.copying.blogs.mapper")
public class CopyingBlogsApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CopyingBlogsApplication.class,args);
    }
}
