package com.ci123.workflow.bean.response.azkaban;

import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;

/**
 * Created by shirukai on 2019-06-01 13:35
 * 登录返回信息
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
