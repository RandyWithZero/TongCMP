package com.tongtech.cmp.web.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * description 静态资源访问配置
 * <p>
 * version 0.1
 * createDate 2019/5/20 17:29
 * updateDate 2019/5/20 17:29
 *
 * @author wangshaoqi
 */


@Configuration
@EnableWebMvc
@ComponentScan
public class StaticResourceRequestConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

