package com.copying.blogs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author copying
 * @date  2020-08-18 15:14:52
 * 文件格式
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "file-type")
public class ReadPhotoTypeConfig {
    private List<String> photoType=new ArrayList<>();
}
