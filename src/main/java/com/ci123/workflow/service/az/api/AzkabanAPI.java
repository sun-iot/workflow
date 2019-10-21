package com.ci123.workflow.service.az.api;

import com.ci123.workflow.bean.response.azkaban.AzBaseResponse;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.az.api
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 17:25
 */
public interface AzkabanAPI {

    AzBaseResponse loginAZ(String username , String password);
    AzBaseResponse createProject(String name, String desc);
}
