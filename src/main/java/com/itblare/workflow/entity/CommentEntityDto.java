package com.itblare.workflow.entity;

import java.util.Date;

/**
 * 评论信息
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/30 12:07
 */
public class CommentEntityDto {

    /**
     * 批注ID
     */
    protected String id;

    /**
     * 类型
     */
    protected String type;

    /**
     * 用户ID
     */
    protected String userId;

    /**
     * 时间
     */
    protected Date time;

    /**
     * 任务ID
     */
    protected String taskId;

    /**
     * 实例ID
     */
    protected String processInstanceId;

    /**
     * 操作
     */
    protected String action;

    /**
     * 批注信息
     */
    protected String message;

    /**
     * 批注信息
     */
    protected String fullMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
