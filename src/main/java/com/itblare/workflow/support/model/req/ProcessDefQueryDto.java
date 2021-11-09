package com.itblare.workflow.support.model.req;

/**
 * 流程定义查询参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/25 16:05
 */
public class ProcessDefQueryDto extends WorkFlowUserDto {

    /**
     * 业务系统标识
     */
    private String tenantId;

    /**
     * 流程定义KEY
     */
    private String processDefinitionKey;

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 流程定义分类
     */
    private String processDefinitionCategory;

    /**
     * 流程定义名称
     */
    private String processDefinitionName;

    /**
     * 流程定义版本
     */
    private Integer version;

    /**
     * 是否是挂起状态
     */
    private Boolean suspended = false;

    /**
     * 是否是激活状态
     */
    private Boolean active = false;

    /**
     * 是否是最终版本
     */
    private Boolean latestVersion = false;

    /**
     * 可启动的用户
     */
    private String bootableByUser;

    /**
     * 可启动的组
     */
    private String bootableBygroup;

    /**
     * 分页参数
     */
    private PageDto pageDto;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionCategory() {
        return processDefinitionCategory;
    }

    public void setProcessDefinitionCategory(String processDefinitionCategory) {
        this.processDefinitionCategory = processDefinitionCategory;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Boolean latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getBootableByUser() {
        return bootableByUser;
    }

    public void setBootableByUser(String bootableByUser) {
        this.bootableByUser = bootableByUser;
    }

    public String getBootableBygroup() {
        return bootableBygroup;
    }

    public void setBootableBygroup(String bootableBygroup) {
        this.bootableBygroup = bootableBygroup;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }
}
