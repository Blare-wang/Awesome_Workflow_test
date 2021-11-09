package com.itblare.workflow.support.model.req;

/**
 * 流程实例公用参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/17 17:19
 */
public class InstanceCommonDto extends WorkFlowUserDto {

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
