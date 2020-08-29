package top.copying.blogs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取 Swagger ui 接口要排除使用token（直接使用） 的接口
 * 过滤URL属性配置类
 * @author copying add
 * @date 2020-08-29 10:40:21
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreUrlProperties {
    private List<String> serverUrls = new ArrayList<>();
    private List<String> commonUrls = new ArrayList<>();

    public List<String> getUrls(){
        List<String> urls = new ArrayList<>(serverUrls);
        urls.addAll(commonUrls);
        return urls;
    }
}
