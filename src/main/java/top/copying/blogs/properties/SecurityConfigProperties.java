package top.copying.blogs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author copying add
 * @date  2020-08-31 11:11:16
 * security 相关配置
 */
@Component
@Data
@RefreshScope
@ConfigurationProperties(prefix = "security-config")
public class SecurityConfigProperties {

    private String username;

    private String password;
    private String username2;

    private String password2;

    private List<String> ignoringPath;

}
