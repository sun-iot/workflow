package com.ci123.workflow.service.proxy.az;

import com.ci123.workflow.service.az.api.AzkabanAPI;
import com.ci123.workflow.service.az.service.AzkabanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.proxy.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 15:54
 */
public class AzkabanInvokeHJandlerBuilder {
    private static Logger log = LoggerFactory.getLogger(AzkabanInvokeHJandlerBuilder.class);
    private String url ;
    private String username ;
    private String password ;

    public AzkabanInvokeHJandlerBuilder() {
    }

    public AzkabanInvokeHJandlerBuilder setUrl(String url) {
        this.url = url;
        return this ;
    }

    public AzkabanInvokeHJandlerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public AzkabanInvokeHJandlerBuilder setPassword(String password) {
        this.password = password;
        return this ;
    }

    public static AzkabanInvokeHJandlerBuilder create(){
        log.info("AzkabanInvokeHJandlerBuilder create() ... ");
        return new AzkabanInvokeHJandlerBuilder();
    }

    public AzkabanAPI builder(){
        AzkabanService server = new AzkabanService(
                this.url,
                this.username,
                this.password);
        AzkabanInvokeHandler handler = new AzkabanInvokeHandler(server);
        log.info("AzkabanInvokeHJandlerBuilder builder() ... ");
        return (AzkabanAPI) Proxy.newProxyInstance(
                server.getClass().getClassLoader(),
                server.getClass().getInterfaces(),
                handler
        );
    }
}
