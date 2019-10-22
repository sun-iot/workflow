package com.ci123.workflow.conifg.az;

import com.ci123.workflow.service.az.api.AzkabanAPI;
import com.ci123.workflow.service.az.service.AzkabanService;
import com.ci123.workflow.service.proxy.az.AzkabanInvokeHJandlerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.conifg.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 16:00
 */
@Configuration
public class AzkabanConfig {
    @Value("${azkaban.url}")
    private String uri;

    @Value("${azkaban.username}")
    private String username;

    @Value("${azkaban.password}")
    private String password;

    @Bean
    public AzkabanAPI azkabanApi() {
        System.out.println(uri);
        return AzkabanInvokeHJandlerBuilder.create()
                .setUrl(uri)
                .setUsername(username)
                .setPassword(password)
                .builder();
    }
}
