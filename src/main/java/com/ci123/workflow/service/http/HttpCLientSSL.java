package com.ci123.workflow.service.http;

import com.ci123.workflow.service.http.ssl.CustomerSecureProtocolSocketFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import java.io.IOException;
import java.util.Map;

public class HttpCLientSSL {

    public void doPost(String url , Map<String,String> param){

    }

    public String doGet(String url) throws Exception {
        ProtocolSocketFactory factory = new CustomerSecureProtocolSocketFactory();
        // 加入相关的Http请求
        Protocol.registerProtocol("https", new Protocol("https", factory, 443));

        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        try {
            client.executeMethod(getMethod);
            return getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }finally {
            getMethod.releaseConnection();
        }

    }
}
