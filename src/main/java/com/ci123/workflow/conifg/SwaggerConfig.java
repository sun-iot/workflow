package com.ci123.workflow.conifg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.conifg
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 16:36
 */
@Configuration
public class SwaggerConfig {
    // 接口版本号
    private final String version = "1.0";
    // 接口大标题
    private final String title = "SpringBoot示例工程";
    // 具体的描述
    private final String description = "API文档自动生成示例";
    // 服务说明url
    private final String termsOfServiceUrl = "http://www.kingeid.com";
    // licence
    private final String license = "MIT";
    // licnce url
    private final String licenseUrl = "https://mit-license.org/";
    // 接口作者联系方式
    private final Contact contact = new Contact("SunYang", "https://github.com/calebman", "chenjianhui0428@gmail.com");

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf())
                .select().build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder().title(title).termsOfServiceUrl(termsOfServiceUrl).description(description)
                .version(version).license(license).licenseUrl(licenseUrl).contact(contact).build();

    }

}
