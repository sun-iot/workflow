package com.ci123.workflow.controller;

import com.ci123.workflow.bean.module.az.Project;

import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;
import com.ci123.workflow.conifg.az.Configuration;
import com.ci123.workflow.service.az.api.AzkabanAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.controller
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 16:10
 */
@Controller
@RequestMapping(value = "/api/v1" ,produces="application/json;charset=utf-8")
@Api(value = "AzkabanController", tags = {"azkaban 的接口 api 示例"})
public class AzkabanController {
    Logger logger = LoggerFactory.getLogger(AzkabanController.class);

    @Autowired
    private AzkabanAPI azkabanAPI ;

    @RequestMapping(value = "/az/manager" , method = RequestMethod.POST )
    @ResponseBody
    @ApiOperation(value = "create a Azkaban Project ", httpMethod = "POST", produces = "application/json" , consumes = "application.json")
    public String createAzkaban(@RequestBody @ApiParam(value = "create a azkaban project" , required = true ) Project project){
        // 发送请求到 Azkaban 服务器
        BaseResponse response = azkabanAPI.createProject(project.getName(), project.getDescription());
        System.out.println("status:" + response.getStatus());
        System.out.println("message:" + response.getMessage());
        System.out.println(Configuration.SESSION_ID);



//        return "\"status\":200,\"msg\":\"OK\"";  //"status":200,"msg":"OK"
        return response.getMessage();
    }
}

