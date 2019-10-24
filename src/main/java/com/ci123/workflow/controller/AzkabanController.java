package com.ci123.workflow.controller;

import com.ci123.workflow.bean.module.az.Project;

import com.ci123.workflow.bean.module.az.Upload;
import com.ci123.workflow.bean.response.azkaban.CreateResponse;
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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/az/manager/create" , method = RequestMethod.POST )
    @ResponseBody
    @ApiOperation(value = "create a Azkaban Project ", httpMethod = "POST", produces = "application/json" , consumes = "application/json")
    public String createProject(@RequestBody @ApiParam(value = "the name of the project and the description of the project " , required = true )Project project){
        System.out.println(project.getDescription());
        CreateResponse response = azkabanAPI.createProject(project.getName(), project.getDescription());
        return response.toString();
    }

    @GetMapping("/az/manager/delete")
    @ResponseBody
    @ApiOperation(value = "delete a Azkaban Project , and no response  ", httpMethod = "GET")
    public void deleteProject(@RequestParam("project") String project){
        BaseResponse response = azkabanAPI.deleteProject(project);
    }

    @PostMapping("/az/manager/upload")
    @ResponseBody
    @ApiOperation(value = "upload the project zip file. The type should be set as application/zip or application/x-zip-compressed",
            httpMethod = "POST" ,
            produces = "application/json",
            consumes = "application/json")
    public String uploadZip(@RequestBody @ApiParam(value = " " , required = true)Upload upload){
        System.out.println(upload.getFile());




        return "" ;
    }



}

