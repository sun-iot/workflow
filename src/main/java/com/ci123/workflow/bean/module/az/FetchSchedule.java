package com.ci123.workflow.bean.module.az;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.bean.module.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/25 16:11
 */
@Data
@Api(value = "fetchSchedule" , produces = "application/json")
public class FetchSchedule {
    @ApiModelProperty(value = "project name" , required = true)
    public String project;
    @ApiModelProperty(value = "flow name" , required = true)
    public String flow ;
}
