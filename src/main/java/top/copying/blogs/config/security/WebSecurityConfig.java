package top.copying.blogs.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author copying
 * @date 2020-08-24 08:25:49
 * Spring Security 配置文件
 * 继承 WebSecurityConfigurerAdapter 即可更改Security默认配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 重写方法以配置{@link WebSecurity}。例如，如果您希望*忽略某些请求。
     * 将配置的地址排除安全认证
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/swagger-ui.html");
    }

    /**
     * http请求 权限规则配置
     * HttpSecurity.RequestMatcherConfigurer 允许映射此{@link HttpSecurity}将用于的HTTP请求
     * securityConfigProperties.getIgnoringPath() 自定义的排除认证地址
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().cors()
                .and().httpBasic();
    }

    /**
     * 在内存中设置默认的用户名，密码
     * @param auth 身份验证管理器生成器
     * @exception Exception is inMemoryAuthentication exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(bCryptPasswordEncoder.encode("admin")).roles("ADMIN")
                .and()
                .withUser("user").password(bCryptPasswordEncoder.encode("user")).roles("USER");
    }

}
