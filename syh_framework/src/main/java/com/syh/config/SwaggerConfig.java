package com.syh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
//@ConditionalOnProperty(value = "swagger.manenabled", havingValue = "true")
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //指定扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.syh.Controller"))
                .build();
                }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("API文档接口测试")    //标题
                .description("本文档描述接口测试用例")  //描述
                .version("1.0")  //版本
                .contact(new Contact("syh", "http://baidu.com", "273032860@qq.com"))
                .build();
    }
}
