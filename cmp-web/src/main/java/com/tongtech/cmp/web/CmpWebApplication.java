package com.tongtech.cmp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * springBoot项目主入口
 *
 * @author wangshaoqi
 */
@EnableSwagger2
@SpringBootApplication
public class CmpWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmpWebApplication.class, args);
	}

}
