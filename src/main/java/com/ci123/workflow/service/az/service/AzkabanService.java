package com.ci123.workflow.service.az.service;

import com.ci123.workflow.bean.response.azkaban.LoginResponse;
import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;
import com.ci123.workflow.bean.response.azkaban.handler.AzResponseHandler;

import com.ci123.workflow.conifg.az.Configuration;
import com.ci123.workflow.service.az.api.AzkabanAPI;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


import java.io.IOException;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 16:12
 */
public class AzkabanService implements AzkabanAPI {
    private static Logger log = LoggerFactory.getLogger(AzkabanService.class);
    private String url ;
    private String username ;
    private String password ;

    public AzkabanService(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public LoginResponse loginAZ() throws IOException {
        Response response = Request.Post(url)
                .bodyForm(Form.form()
                        .add("action", "login")
                        .add("username", username)
                        .add("password", password)
                        .build()).execute();
        HttpEntity entity = response.returnResponse().getEntity();
        String sessionId = EntityUtils.toString(entity).replace("session.id", "sessionId");
        LoginResponse loginResponse = AzResponseHandler.handle(sessionId, LoginResponse.class);

        if (!(StringUtils.isEmpty(loginResponse.getSessionId()))){
            Configuration.SESSION_ID = loginResponse.getSessionId() ;
        }else {
            log.error("Execute the azkaban's API {} failed.");
        }
        System.out.println("login.....");
        return loginResponse ;
    }


    @Override
    public BaseResponse createProject(String name, String desc) {
        Request request = Request.Post(url + "/manager").bodyForm(
                Form.form().add("session.id", Configuration.SESSION_ID)
                        .add("action", "create")
                        .add("name", name)
                        .add("description", desc).build()
        );
        return AzResponseHandler.handle(request);
    }
}
