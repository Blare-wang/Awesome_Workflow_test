package com.itblare.workflow.support.model.req;

/**
 * 候选人/组参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/22 11:21
 */
public class CandidateDto {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 候选人
     */
    private String candidateUser;

    /**
     * 候选组
     */
    private String candidateGroup;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(String candidateUser) {
        this.candidateUser = candidateUser;
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup;
    }
}
