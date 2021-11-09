package com.itblare.workflow.support.model.resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 任务信息dto
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 15:39
 */
public class TaskDto {

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 任务ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类【标识】
     */
    private String category;

    /**
     * 被代理人【委托的时候才有】
     */
    private String owner;

    /**
     * 经办人【委托人】
     */
    private String assignee;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 签收时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date claimTime;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dueDate;

    private Long durationInMillis;
    private Long workTimeInMillis;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 任务定义KEY
     */
    private String taskDefinitionKey;

    /**
     * 父任务ID
     */
    private String parentTaskId;

    /**
     * 父任务名称
     */
    private String parentTaskName;

    /**
     * 任务表单KEY
     */
    private String formKey;

    /**
     * 启动人可完成任务
     */
    private String initiatorCanCompleteTask;

    /**
     * 候选组
     */
    private String memberOfCandidateGroup;

    /**
     * 候选成员
     */
    private String memberOfCandidateUsers;

    /**
     * 委派状态
     */
    private String delegationState;

    /**
     * 是否挂起
     */
    private boolean suspended;

    /**
     * 参与人
     */
    private List<String> involvedPeople;

    /**
     * 被代理人名称
     */
    private String ownerName;

    /**
     * 执行人名称
     */
    private String assigneeName;

    /**
     * 评注信息
     */
    private List<ProcCommentDto> userCommentDtoList;

    /**
     * 执行评注信息
     */
    private List<ProcCommentDto> execCommentDtoList;

    /**
     * 审核评注信息
     */
    private List<ProcCommentDto> auditCommentDtoList;

    /**
     * 附件信息
     */
    private List<ProcAttachmentDto> attachmentDtoList;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public Long getWorkTimeInMillis() {
        return workTimeInMillis;
    }

    public void setWorkTimeInMillis(Long workTimeInMillis) {
        this.workTimeInMillis = workTimeInMillis;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getInitiatorCanCompleteTask() {
        return initiatorCanCompleteTask;
    }

    public void setInitiatorCanCompleteTask(String initiatorCanCompleteTask) {
        this.initiatorCanCompleteTask = initiatorCanCompleteTask;
    }

    public String getMemberOfCandidateGroup() {
        return memberOfCandidateGroup;
    }

    public void setMemberOfCandidateGroup(String memberOfCandidateGroup) {
        this.memberOfCandidateGroup = memberOfCandidateGroup;
    }

    public String getMemberOfCandidateUsers() {
        return memberOfCandidateUsers;
    }

    public void setMemberOfCandidateUsers(String memberOfCandidateUsers) {
        this.memberOfCandidateUsers = memberOfCandidateUsers;
    }

    public String getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(String delegationState) {
        this.delegationState = delegationState;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public List<String> getInvolvedPeople() {
        return involvedPeople;
    }

    public void setInvolvedPeople(List<String> involvedPeople) {
        this.involvedPeople = involvedPeople;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public List<ProcCommentDto> getUserCommentDtoList() {
        return userCommentDtoList;
    }

    public void setUserCommentDtoList(List<ProcCommentDto> userCommentDtoList) {
        this.userCommentDtoList = userCommentDtoList;
    }

    public List<ProcCommentDto> getExecCommentDtoList() {
        return execCommentDtoList;
    }

    public void setExecCommentDtoList(List<ProcCommentDto> execCommentDtoList) {
        this.execCommentDtoList = execCommentDtoList;
    }

    public List<ProcCommentDto> getAuditCommentDtoList() {
        return auditCommentDtoList;
    }

    public void setAuditCommentDtoList(List<ProcCommentDto> auditCommentDtoList) {
        this.auditCommentDtoList = auditCommentDtoList;
    }

    public List<ProcAttachmentDto> getAttachmentDtoList() {
        return attachmentDtoList;
    }

    public void setAttachmentDtoList(List<ProcAttachmentDto> attachmentDtoList) {
        this.attachmentDtoList = attachmentDtoList;
    }
}
