package com.ci123.workflow.bean.response.azkaban;

import com.ci123.workflow.bean.response.azkaban.base.BaseResponse;

/**
 * Created by shirukai on 2019-06-04 10:09
 * 定时调度Flow响应
 */
public class ScheduleCronFlowResponse extends BaseResponse {
    private String scheduleId;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
}
