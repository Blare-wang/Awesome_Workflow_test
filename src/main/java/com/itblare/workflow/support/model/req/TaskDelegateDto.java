package com.itblare.workflow.support.model.req;

/**
 * 流程任务委托参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/18 10:09
 */
public class TaskDelegateDto extends TaskCommonDto {

    /**
     * 委派人
     */
    private String delegator;

    public String getDelegator() {
        return delegator;
    }

    public void setDelegator(String delegator) {
        this.delegator = delegator;
    }
}
