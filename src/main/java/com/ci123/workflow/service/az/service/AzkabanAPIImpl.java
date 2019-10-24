package com.ci123.workflow.service.az.service;

import com.ci123.workflow.bean.response.azkaban.*;
import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;
import com.ci123.workflow.conifg.az.Configuration;
import com.ci123.workflow.service.az.api.AzkabanAPI;
import com.ci123.workflow.service.http.GetHttpClientSSL;
import com.ci123.workflow.service.http.PostClientSSL;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Response;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.az.service
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 11:09
 */
public class AzkabanAPIImpl implements AzkabanAPI {
    private String username;
    private String password;
    private String uri;
    private String sessionId = "b1d4f665-f4b9-4e7d-b83a-b928b41cc323";
    private PostClientSSL postClientSSL;
    private GetHttpClientSSL getClientSSL;

    public AzkabanAPIImpl(String uri, String username, String password) {
        this.uri = uri;
        this.username = username;
        this.password = password;
        postClientSSL = new PostClientSSL();
        getClientSSL = new GetHttpClientSSL();
    }

    @Override
    public CreateResponse createProject(String name, String desc) {

        Map<String, String> body = new HashMap<>();
        body.put("session.id", sessionId);
        body.put("action", "create");
        body.put("name", name);
        body.put("description", desc);
        String result = postClientSSL.doPost(uri + "/manager", body);
        return ResponseHandler.handle(result, CreateResponse.class);
    }

    @Override
    public BaseResponse deleteProject(String name) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.DELETE_PROJECT, uri, name, sessionId));
        // 在进行 删除 project 时， Azkaban不会返回任何值
        result="\"status\":\"success\",\"message\":\"delete project successfully\"" ;
        return ResponseHandler.handle(result);
    }

    @Override
    public ProjectZipResponse uploadProjectZip(String filePath, String projectName) {

        return null;
    }

    @Override
    public FetchFlowsResponse fetchProjectFlows(String projectName) {
        return null;
    }

    @Override
    public ExecuteFlowResponse executeFlow(String projectName, String flowName) {
        return null;
    }

    @Override
    public BaseResponse cancelFlow(String execId) {
        return null;
    }

    @Override
    public FetchExecFlowResponse fetchExecFlow(String execId) {
        return null;
    }

    @Override
    public FetchExecJobLogs fetchExecJobLogs(String execId, String jobId, int offset, int length) {
        return null;
    }

    @Override
    public FetchFlowExecutionsResponse fetchFlowExecutions(String projectName, String flowName, int start, int length) {
        return null;
    }

    @Override
    public FetchAllProjectsResponse fetchAllProjects() {
        return null;
    }

    @Override
    public ScheduleCronFlowResponse scheduleCronFlow(String projectName, String flowName, String cronExpression) {
        return null;
    }

    @Override
    public FetchScheduleResponse fetchSchedule(String projectId, String flowId) {
        return null;
    }

    @Override
    public BaseResponse removeSchedule(String scheduleId) {
        return null;
    }

    public LoginResponse login() {
        Response res = null;
        LoginResponse response = null;
        HttpEntity entity = null;
        try {
            Map<String, String> body = new HashMap<>();
            body.put("action", "login");
            body.put("username", username);
            body.put("password", password);
            String result = postClientSSL.doPost(uri, body);
            String content = result.replace("session.id", "sessionId");
            response = ResponseHandler.handle(content, LoginResponse.class);
        } catch (Exception e) {
            System.out.println("loginException:message-> " + e.getMessage() + "\n" +
                    "loginException:cause" + e.getCause().getMessage());
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(response.getSessionId())) {
            this.sessionId = response.getSessionId();
            System.out.println(this.sessionId);
        } else {
            System.out.println("获取session 失败");
        }
        return response;
    }
}
