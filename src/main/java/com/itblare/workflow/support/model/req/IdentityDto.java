package com.itblare.workflow.support.model.req;

import java.util.Set;

/**
 * 身份授权信息参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/24 15:36
 */
public class IdentityDto {

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 授权身份信息
     */
    private Set<String> identityId;

    /**
     * 授权身份类型
     */
    private int identityType;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Set<String> getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Set<String> identityId) {
        this.identityId = identityId;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }
}
