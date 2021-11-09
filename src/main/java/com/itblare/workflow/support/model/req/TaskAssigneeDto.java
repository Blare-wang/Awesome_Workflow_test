package com.itblare.workflow.support.model.req;
/**
 * 任务转办参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/1 14:16
 */
public class TaskAssigneeDto extends TaskCommonDto {

    /**
     * 转办人
     */
    private String assignee;

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
