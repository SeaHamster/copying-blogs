package com.copying.blogs.config;

import com.copying.blogs.security.JwtAccessDeniedHandler;
import com.copying.blogs.security.JwtAuthenticationEntryPoint;
import com.copying.blogs.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author fzz
 * @date 2022/8/5
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //spring方法级安全时
public class SecurityJwtConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests()
                //不拦截
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/", "/static/**", "/css/**", "/images/**", "/js/**", "/lib/**").permitAll()
                .antMatchers("/captchaImage", "/login", "/oauth/login/**").permitAll()
                .antMatchers("/archives", "/archives/**").permitAll()
                .antMatchers("/comments", "/comments/**").permitAll()
                .antMatchers("/friends", "/friends/**").permitAll()
                .antMatchers("/blog", "/blog/**").permitAll()
                .antMatchers("/tags", "/tags/**").permitAll()
                .antMatchers("/types", "/types/**").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll();
                //授权
                .and()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 使用自己定义的拦截机制，拦截jwt
        http.addFilterAfter(new JwtAuthenticationFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                //授权错误信息处理
                .exceptionHandling()
                //用户访问资源没有携带正确的token
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //用户访问没有授权资源
                .accessDeniedHandler(jwtAccessDeniedHandler);

    }

}
