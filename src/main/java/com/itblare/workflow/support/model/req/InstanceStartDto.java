package com.itblare.workflow.support.model.req;

import java.util.List;
import java.util.Map;

/**
 * 流程实例启动参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/17 17:19
 */
public class InstanceStartDto extends InstanceCommonDto {

    /**
     * 自定义实例名称
     */
    private String name;

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 流程定义KEY
     */
    private String processDefinitionKey;

    /**
     * 业务系统标识ID
     */
    private String tenantId;

    /**
     * 业务标识KEY
     */
    private String businessKey;

    /**
     * 流程变量
     */
    private Map<String, Object> variables;

    /**
     * 是否设置发起人，默认为true
     */
    private boolean addAuthenticatedUserId = true;

    /**
     * 抄送
     */
    private List<CcDto> ccTotoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public boolean isAddAuthenticatedUserId() {
        return addAuthenticatedUserId;
    }

    public void setAddAuthenticatedUserId(boolean addAuthenticatedUserId) {
        this.addAuthenticatedUserId = addAuthenticatedUserId;
    }

    public List<CcDto> getCcTotoList() {
        return ccTotoList;
    }

    public void setCcTotoList(List<CcDto> ccTotoList) {
        this.ccTotoList = ccTotoList;
    }
}
