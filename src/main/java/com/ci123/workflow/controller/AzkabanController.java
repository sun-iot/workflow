package com.ci123.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value = "/api/v1", produces="application/json,charset=utf-8")
public class AzkabanController {

    @RequestMapping("az/create")
    @ResponseBody
    public String createJob(){
        return "" ; //
    }
}
