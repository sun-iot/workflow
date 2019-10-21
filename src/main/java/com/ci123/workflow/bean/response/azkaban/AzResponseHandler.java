package com.ci123.workflow.bean.response.azkaban;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Objects;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p> 进行统一响应
 * Project: workflow
 * Package: com.ci123.workflow.bean.response
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 17:17
 */
public class AzResponseHandler {
    private static Logger log = LoggerFactory.getLogger(AzResponseHandler.class);

    public static <T extends AzBaseResponse> T handle(Request request, Class<T> tClass) {
        T response = null;
        try {
            Response res = request.execute();
            HttpEntity entity  = res.returnResponse().getEntity();
            response = handle(entity, tClass);
        } catch (Exception e) {
            try {
                response = tClass.newInstance();
                response.setStatus(T.ERROR);
                response.setError(e.getMessage());
                response.correction();
            } catch (Exception ea) {
                log.warn(ea.getMessage());
            }
        }
        return response;
    }

    public static AzBaseResponse handle(Request request) {
        return handle(request, AzBaseResponse.class);
    }


    public static AzBaseResponse handle(String content) {
        return handle(content, AzBaseResponse.class);
    }

    public static <T extends AzBaseResponse> T handle(HttpEntity entity, Class<T> tClass) {
        T response = null;
        try {
            String content = EntityUtils.toString(entity);
            response = handle(content, tClass);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return response;
    }

    public static <T extends AzBaseResponse> T handle(String content, Class<T> tClass) {
        T response = null;
        try {
            response = JSONObject.parseObject(content, tClass);
            if (Objects.nonNull(response.getError())) {
                response.setStatus(T.ERROR);
            }
            response.correction();
        } catch (Exception e) {
            try {
                response = tClass.newInstance();
                response.setStatus(T.ERROR);
                response.setError(content);
                response.correction();
            } catch (Exception ea) {
                log.warn(ea.getMessage());
            }
        }
        return response;
    }

}
