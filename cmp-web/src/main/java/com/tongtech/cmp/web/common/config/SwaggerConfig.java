package com.tongtech.cmp.web.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.InetAddress;

/**
 * description Swagger配置类
 * <p>
 * version 0.1
 * createDate 2019/5/17 16:10
 * updateDate 2019/5/17 16:10
 *
 * @author wangshaoqi
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() throws Exception {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tongtech.cmp.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() throws Exception {
        return new ApiInfoBuilder()
                .title("TongCMP API文档")
                .description("TongCMP API使用说明")
                .termsOfServiceUrl("http://" + InetAddress.getLocalHost().getHostAddress())
                .version("0.1")
                .build();

    }
}
