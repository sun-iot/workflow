package com.ci123.workflow.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jws.WebResult;

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
@RequestMapping(value = "/api/v1", produces="application/json,charset=utf-8")
@Api(value = "AzkabanController", tags = {"azkaban 的接口 api 示例"})
public class AzkabanController {
    Logger logger = LoggerFactory.getLogger(AzkabanController.class);

    @RequestMapping(value = "/az/login" , method = RequestMethod.POST )
    @ResponseBody
    @ApiOperation(value = "登陆到 Azkaban ", httpMethod = "POST", produces = "application/json" ,consumes = "application.json")
    public String loginAzkaban(){
        return "\"state\": 200 , \"msg\":\"OK\"";
    }
}

