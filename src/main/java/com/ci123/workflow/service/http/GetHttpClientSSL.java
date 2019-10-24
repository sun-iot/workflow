package com.ci123.workflow.service.http;

import com.ci123.workflow.service.http.ssl.HttpClientSSL;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.http
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 17:21
 */
public class GetHttpClientSSL {
    private Logger logger = LoggerFactory.getLogger(GetHttpClientSSL.class);
    public String doGet(String url) {
        String result = null;
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            response = httpClient.execute(httpGet);

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
            //logger.info(result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            release(httpClient);
        }
        return null;
    }

    /**
     * 释放
     *
     * @param httpClient
     */
    private void release(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
