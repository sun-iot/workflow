package com.ci123.workflow.service.az.service;

import com.ci123.workflow.bean.response.azkaban.AzBaseResponse;
import com.ci123.workflow.bean.response.azkaban.AzResponseHandler;
import com.ci123.workflow.service.az.api.AzkabanAPI;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

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
    private String url = "";
    private String username ;
    private String password ;

    @Override
    public AzBaseResponse loginAZ(String username, String password) {
        Request request = Request.Post(url)
                .bodyForm(Form.form().add("username", username)
                        .add("password", password)
                        .build());
        return AzResponseHandler.handle(request);

    }


    @Override
    public AzBaseResponse createProject(String name, String desc) {
        return null;
    }
}
