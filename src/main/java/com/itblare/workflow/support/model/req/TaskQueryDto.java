package com.itblare.workflow.support.model.req;

/**
 * 任务查询参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/28 14:54
 */
public class TaskQueryDto extends WorkFlowUserDto {

    /**
     *
     */
    private String taskId;

    /**
     *
     */
    private String taskName;

    /**
     *
     */
    private String taskCategory;

    /**
     *
     */
    private String taskDescription;

    /**
     *
     */
    private String taskDefinitionKey;

    /**
     *
     */
    private String taskAssignee;

    /**
     *
     */
    private String taskOwner;

    /**
     *
     */
    private String taskInvolvedUser;

    /**
     *
     */
    private Integer taskPriority;

    /**
     *
     */
    private Boolean unfinished = false;

    /**
     *
     */
    private String executionId;

    /**
     *
     */
    private String processInstanceId;

    /**
     *
     */
    private String processInstanceName;

    /**
     *
     */
    private String processDefinitionName;

    /**
     *
     */
    private String processDefinitionKey;

    /**
     *
     */
    private String processDefinitionId;

    /**
     *
     */
    private String processInstanceBusinessKey;

    /**
     *
     */
    private String processCategoryIn;

    /**
     *
     */
    private String processCategoryNotIn;

    /**
     *
     */
    private Boolean processUnfinished = false;

    /**
     *
     */
    private String taskParentTaskId;

    /**
     *
     */
    private String taskCandidateUser;

    /**
     *
     */
    private String taskCandidateGroup;

    /**
     *
     */
    private String taskCandidateGroupIn;

    /**
     *
     */
    private String taskDueAfter;

    /**
     *
     */
    private String taskDueBefore;

    /**
     *
     */
    private String taskCreatedAfter;

    /**
     *
     */
    private String taskCreatedBefore;

    /**
     *
     */
    private String taskCompletedAfter;

    /**
     *
     */
    private String taskCompletedBefore;

    /**
     *
     */
    private Boolean suspended = false;

    /**
     *
     */
    private Boolean active = false;

    /**
     * 分页参数
     */
    private PageDto pageDto;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskInvolvedUser() {
        return taskInvolvedUser;
    }

    public void setTaskInvolvedUser(String taskInvolvedUser) {
        this.taskInvolvedUser = taskInvolvedUser;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Boolean getUnfinished() {
        return unfinished;
    }

    public void setUnfinished(Boolean unfinished) {
        this.unfinished = unfinished;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
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

    public String getProcessInstanceBusinessKey() {
        return processInstanceBusinessKey;
    }

    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }

    public String getProcessCategoryIn() {
        return processCategoryIn;
    }

    public void setProcessCategoryIn(String processCategoryIn) {
        this.processCategoryIn = processCategoryIn;
    }

    public String getProcessCategoryNotIn() {
        return processCategoryNotIn;
    }

    public void setProcessCategoryNotIn(String processCategoryNotIn) {
        this.processCategoryNotIn = processCategoryNotIn;
    }

    public Boolean getProcessUnfinished() {
        return processUnfinished;
    }

    public void setProcessUnfinished(Boolean processUnfinished) {
        this.processUnfinished = processUnfinished;
    }

    public String getTaskParentTaskId() {
        return taskParentTaskId;
    }

    public void setTaskParentTaskId(String taskParentTaskId) {
        this.taskParentTaskId = taskParentTaskId;
    }

    public String getTaskCandidateUser() {
        return taskCandidateUser;
    }

    public void setTaskCandidateUser(String taskCandidateUser) {
        this.taskCandidateUser = taskCandidateUser;
    }

    public String getTaskCandidateGroup() {
        return taskCandidateGroup;
    }

    public void setTaskCandidateGroup(String taskCandidateGroup) {
        this.taskCandidateGroup = taskCandidateGroup;
    }

    public String getTaskCandidateGroupIn() {
        return taskCandidateGroupIn;
    }

    public void setTaskCandidateGroupIn(String taskCandidateGroupIn) {
        this.taskCandidateGroupIn = taskCandidateGroupIn;
    }

    public String getTaskDueAfter() {
        return taskDueAfter;
    }

    public void setTaskDueAfter(String taskDueAfter) {
        this.taskDueAfter = taskDueAfter;
    }

    public String getTaskDueBefore() {
        return taskDueBefore;
    }

    public void setTaskDueBefore(String taskDueBefore) {
        this.taskDueBefore = taskDueBefore;
    }

    public String getTaskCreatedAfter() {
        return taskCreatedAfter;
    }

    public void setTaskCreatedAfter(String taskCreatedAfter) {
        this.taskCreatedAfter = taskCreatedAfter;
    }

    public String getTaskCreatedBefore() {
        return taskCreatedBefore;
    }

    public void setTaskCreatedBefore(String taskCreatedBefore) {
        this.taskCreatedBefore = taskCreatedBefore;
    }

    public String getTaskCompletedAfter() {
        return taskCompletedAfter;
    }

    public void setTaskCompletedAfter(String taskCompletedAfter) {
        this.taskCompletedAfter = taskCompletedAfter;
    }

    public String getTaskCompletedBefore() {
        return taskCompletedBefore;
    }

    public void setTaskCompletedBefore(String taskCompletedBefore) {
        this.taskCompletedBefore = taskCompletedBefore;
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

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }
}
