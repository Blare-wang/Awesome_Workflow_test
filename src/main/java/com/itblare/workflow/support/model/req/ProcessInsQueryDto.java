package com.itblare.workflow.support.model.req;

import java.time.LocalDateTime;

/**
 * 流程实例查询参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/28 14:06
 */
public class ProcessInsQueryDto extends WorkFlowUserDto{

    /**
     * 流程定义分类
     */
    private String processDefinitionCategory;

    /**
     * 流程定义名称
     */
    private String processDefinitionName;

    /**
     * 流程定义KEY
     */
    private String processDefinitionKey;

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 业务系统标识ID
     */
    private String tenantId;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 流程实例名称
     */
    private String processInstanceName;

    /**
     * 业务标识KEY
     */
    private String businessKey;

    /**
     * 关联用户
     */
    private String involvedUser;

    /**
     * 父流程实例ID
     */
    private String superProcessInstanceId;

    /**
     * 是否从查询结果中排除子进程
     */
    private Boolean excludeSubprocesses;

    /**
     * 完成之后
     */
    private LocalDateTime finishedAfter;

    /**
     * 完成之前
     */
    private LocalDateTime finishedBefore;

    /**
     * 启动之后
     */
    private LocalDateTime startedAfter;

    /**
     * 启动之前
     */
    private LocalDateTime startedBefore;

    /**
     * 由某人启动
     */
    private String startedBy;

    /**
     * 抄送给某人
     */
    private String ccTo;

    /**
     * 是否完成
     */
    private Boolean unfinished;

    /**
     * 是否逻辑删除
     */
    private Boolean deleted;

    /**
     * 分页参数
     */
    private PageDto pageDto;

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceName() {
        return processInstanceName;
    }

    public void setProcessInstanceName(String processInstanceName) {
        this.processInstanceName = processInstanceName;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getInvolvedUser() {
        return involvedUser;
    }

    public void setInvolvedUser(String involvedUser) {
        this.involvedUser = involvedUser;
    }

    public String getSuperProcessInstanceId() {
        return superProcessInstanceId;
    }

    public void setSuperProcessInstanceId(String superProcessInstanceId) {
        this.superProcessInstanceId = superProcessInstanceId;
    }

    public Boolean getExcludeSubprocesses() {
        return excludeSubprocesses;
    }

    public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
        this.excludeSubprocesses = excludeSubprocesses;
    }

    public LocalDateTime getFinishedAfter() {
        return finishedAfter;
    }

    public void setFinishedAfter(LocalDateTime finishedAfter) {
        this.finishedAfter = finishedAfter;
    }

    public LocalDateTime getFinishedBefore() {
        return finishedBefore;
    }

    public void setFinishedBefore(LocalDateTime finishedBefore) {
        this.finishedBefore = finishedBefore;
    }

    public LocalDateTime getStartedAfter() {
        return startedAfter;
    }

    public void setStartedAfter(LocalDateTime startedAfter) {
        this.startedAfter = startedAfter;
    }

    public LocalDateTime getStartedBefore() {
        return startedBefore;
    }

    public void setStartedBefore(LocalDateTime startedBefore) {
        this.startedBefore = startedBefore;
    }

    public String getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(String startedBy) {
        this.startedBy = startedBy;
    }

    public String getCcTo() {
        return ccTo;
    }

    public void setCcTo(String ccTo) {
        this.ccTo = ccTo;
    }

    public Boolean getUnfinished() {
        return unfinished;
    }

    public void setUnfinished(Boolean unfinished) {
        this.unfinished = unfinished;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }
}
