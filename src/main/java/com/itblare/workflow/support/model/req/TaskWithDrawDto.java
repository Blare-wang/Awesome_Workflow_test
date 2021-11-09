package com.itblare.workflow.support.model.req;

/**
 * 流程任务回撤参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/1 14:13
 */
public class TaskWithDrawDto extends TaskCommonDto {

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
