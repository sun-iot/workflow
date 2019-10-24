package com.ci123.workflow.service.http;

import com.ci123.workflow.service.http.ssl.HttpClientSSL;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.io.IOException;


/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.http
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 13:46
 */
public class PostClientSSL {
    private Logger logger = LoggerFactory.getLogger(GetHttpClientSSL.class);
    /**
     * @param url
     * @param contentMap
     * @return
     */
    public String doPost(String url, Map<String, String> contentMap) {
        String result = null;
        CloseableHttpClient httpClient = HttpClientSSL.buildSSLCloseableHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> content = new ArrayList<>();
        Iterator iterator = contentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            content.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        CloseableHttpResponse response = null;
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            if (content.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content,"UTF-8");
                post.setEntity(entity);
            }
            response = httpClient.execute(post);

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
            logger.info(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.abort();
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            release(httpClient);
        }
        return null;
    }

    /**
     * 释放
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
