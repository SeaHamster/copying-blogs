package top.copying.blogs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author copying
 * @date 2020-08-17 10:01:19
 * 读取全局配置文件信息
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "global-config")
public class ReadGlobalConfig {

    private Map<String,String> filePath=new HashMap<>();

}
