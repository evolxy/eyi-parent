package com.xu.server.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/15 17:07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("eyiAdmin")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xu.server.admin")) //指定提供接口所在的基包
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("Eyi Admin 接口文档")
                .contact(new Contact("夜静月明","#","xu.yupeng@qq.com"))
                .version("0.1")
                .description("eyi admin")
                .build();
    }

    @Bean
    public Docket docketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("eyiApi ")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xu.server.api")) //指定提供接口所在的基包
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Eyi Api 接口文档")
                .contact(new Contact("夜静月明","#","xu.yupeng@qq.com"))
                .version("0.1")
                .description("eyi api")
                .build();
    }
}
