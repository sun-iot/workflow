package com.ci123.workflow.oozie.config;

import com.ci123.workflow.oozie.service.api.OozieAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.oozie.config
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/28 15:30
 */
@Configuration
public class OozieConfig {

    @Value("${oozie.url}")
    private String oozieUrl ;

    public OozieAPI oozieAPI(){
        return null ;
    }


}
