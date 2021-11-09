package com.itblare.workflow.support.model.req;

import java.time.LocalDateTime;

/**
 * 流程定义参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 15:35
 */
public class ProcessDefinitionDto {

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 是否包括实例
     */
    private boolean includeProcessInstances = false;

    /**
     * 时间
     */
    private LocalDateTime date;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public boolean isIncludeProcessInstances() {
        return includeProcessInstances;
    }

    public void setIncludeProcessInstances(boolean includeProcessInstances) {
        this.includeProcessInstances = includeProcessInstances;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
