package com.jzqm.inviteme.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //生成api文档扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.jzqm.inviteme.base.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("yiyuanshanhe Api")
                //作者
                .contact(new Contact("yiyuanshanhe", "https://www.yiyuanshanhe.cn", ""))
                //版本号
                .version("1.0")
                //描述
                .description("Base Java 1.8")
                .build();
    }
}

