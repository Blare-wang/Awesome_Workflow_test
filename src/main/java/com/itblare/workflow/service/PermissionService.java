package com.itblare.workflow.service;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;

/**
 * 执行权限鉴定服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/11 1:26
 */
public interface PermissionService {

    /**
     * 是否挂起中任务
     *
     * @param task 任务对象
     * @return {@link boolean}
     * @author Blare
     */
    boolean isPendingTask(Task task);

    /**
     * 是否是任务的所有者或者执行人
     *
     * @param currUserId 用户ID
     * @param task       任务对象
     * @return {@link boolean}
     * @author Blare
     */
    boolean isTaskOfOwnerOrAssignee(String currUserId, Task task);

    /**
     * 是否流程启动人，且流程启动人是否可以完成任务
     *
     * @param currUserId 用户ID
     * @param task       任务对象
     * @return {@link boolean}
     * @author Blare
     */
    boolean validateIfUserIsInitiatorAndCanCompleteTask(String currUserId, Task task);

    ProcessDefinition validateReadPermissionOnProcessDefinition(String userId, String processDefinitionId, String processDefinitionKey, String tenantId);

    void validateReadPermissionOnTask(String id, String userId, boolean b, boolean b1);
}