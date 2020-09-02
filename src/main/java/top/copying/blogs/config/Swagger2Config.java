package top.copying.blogs.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author copying
 * @date
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * apis扫描com路径下的api文档.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
     * paths路径判断
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.copying.blogs"))
                .paths(PathSelectors.any())
                .build();
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

}
