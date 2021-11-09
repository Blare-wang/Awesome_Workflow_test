package com.itblare.workflow.support.model.resp;

/**
 * 批注信息
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 11:53
 */
public class ProcCommentDto {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户基本信息
     */
    private UserInfoDto userInfoDto;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 批注信息类型
     */
    private String type;

    /**
     * 批注信息
     */
    private String message;

    /**
     * 长批注信息
     */
    private String fullMessage;

    /**
     * 新建时间
     */
    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfoDto getUserInfoDto() {
        return userInfoDto;
    }

    public void setUserInfoDto(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
