package com.ci123.workflow.service.http.ssl;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.springframework.web.client.HttpClientErrorException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright (c) 2019- Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.http.ssl
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 下午9:24
 */
public class CustomerSecureProtocolSocketFactory implements SecureProtocolSocketFactory {
    // 用于跳过验证
    private SSLContext sslContext = null;

    public CustomerSecureProtocolSocketFactory() {
    }

    public static SSLContext createSSLCntext(){
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { new HttpX509TrustManager() },
                    null);
            return sslContext ;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new HttpClientError(e.toString());
        }

    }
    public SSLContext getSSLContext(){
        if (this.sslContext == null ){
            sslContext = createSSLCntext();
        }
        return this.sslContext ;
    }

    /**
     *
     * @param socket socket
     * @param s host
     * @param i port
     * @param b autoCLose
     * @return Socket
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(socket , s , i , b);
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(s , i , inetAddress , i1);
    }

    /**
     *
     * @param s host
     * @param i port
     * @param inetAddress localAddress
     * @param i1 localPort
     * @param httpConnectionParams
     * @return
     * @throws IOException
     * @throws UnknownHostException
     * @throws ConnectTimeoutException
     */
    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1, HttpConnectionParams httpConnectionParams) throws IOException, UnknownHostException, ConnectTimeoutException {
        if (httpConnectionParams == null ){
            throw new IllegalArgumentException("HttpConnectionParams can not be null");
        }
        int timeout = httpConnectionParams.getConnectionTimeout();
        if (timeout == 0 ){
            return createSocket(s , i , inetAddress , i1) ;
        }else{
            return ControllerThreadSocketFactory.createSocket(this , s , i , inetAddress , i1 , timeout) ;
        }
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        return null;
    }
}
