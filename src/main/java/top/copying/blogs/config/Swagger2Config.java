package top.copying.blogs.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.copying.blogs.properties.IgnoreUrlProperties;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author copying
 * @date 2020-08-29 10:45:36
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /** 过滤的URL */
    @Resource
    private IgnoreUrlProperties ignoreUrlProperties;
    /**
     * apis扫描com路径下的api文档.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
     *  .apis(RequestHandlerSelectors.basePackage("top.copying.blogs"))
     * paths路径判断
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    /**
     * title标题
     * description 描述
     * termsOfServiceUrl（不可见）条款地址
     * version版本号
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Copying Blogs Service")
                .description("个人博客功能接口")
                .termsOfServiceUrl("http://139.155.80.168:10505/index.html")
                .version("1.0")
                .build();
    }

    /**
     * 2020-08-29 09:48:35 copying add
     * 添加在Swagger ui中实现登录功能
     * securitySchemes()方法配置里增加需要token的配置。
     * 配置完成后，swagger-ui.html里右上角会有一个Authorize的按钮，录入该token即能成功调用相关接口
     * //.grantTypes(Collections.singletonList(grantType))
     * @return SecurityScheme
     */
    private SecurityScheme securityScheme(){
        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(grantTypes())
                .scopes(Arrays.asList(scopes()))
                .build();

    }

    /**
     * 设置使用什么模式
     * 当前为密码模式
     * @return List<GrantType>
     */
    private List<GrantType> grantTypes(){
        List<GrantType> grantTypes = new ArrayList<>();
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/blogs/oauth/token");
        grantTypes.add(grantType );
        return grantTypes;
    }

    /**
     * 设置 swagger2 认证的安全上下文
     * //.forPaths(PathSelectors.any()) 所有接口加锁
     * .forPaths(Predicates.not(Predicates.in(ignoreUrlProperties.getUrls()))) 加锁排除指定的接口地址
     * @return SecurityContext
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("oauth2", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }


    /**
     * 设置认证的scope
     * @return AuthorizationScope[]
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "All scope is trusted!")
        };
    }


}
