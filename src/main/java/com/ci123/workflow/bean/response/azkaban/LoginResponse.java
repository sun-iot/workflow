package com.ci123.workflow.bean.response.azkaban;

import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.bean.response.azkaban
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 15:04
 */
public class LoginResponse extends BaseResponse {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
