package com.ci123.workflow.service.azkaban.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.az.util
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/25 9:43
 */
public class MapBody {
    List<NameValuePair> postBody = new ArrayList<>();
    public static MapBody create(){
        return new MapBody();
    }
    MapBody(){
        this.postBody = null ;
    }

    public MapBody addBody(String key , String value){
        this.postBody.add(new BasicNameValuePair(key , value)) ;
        return this ;
    }

    public List<NameValuePair> build(){
        return this.postBody ;
    }

}
