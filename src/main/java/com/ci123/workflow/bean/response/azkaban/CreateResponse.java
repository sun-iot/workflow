package com.ci123.workflow.bean.response.azkaban;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.bean.response.azkaban
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 16:52
 */

public class CreateResponse extends BaseResponse {
    private String status ;
    private String message ;
    private String path ;
    private String action ;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString(){
//        JSONObject json = new JSONObject();
//        json.put("status" , status);
//        json.put("message" , message);
//        json.put("path" , path);
//        json.put("action" , action);
//        return json.toString() ;
        return JSON.toJSONString(this).toString() ;
    }
}
