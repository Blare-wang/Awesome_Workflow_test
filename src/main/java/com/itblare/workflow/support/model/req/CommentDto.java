package com.itblare.workflow.support.model.req;

/**
 * 批注信息参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/18 11:53
 */
public class CommentDto {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 批注信息
     */
    private String message;

    /**
     * 长批注信息
     */
    private String fullMessage;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }
}
