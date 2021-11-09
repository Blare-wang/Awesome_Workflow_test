package com.itblare.workflow.service;

import com.itblare.workflow.support.enumerate.ButtonType;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Set;

/**
 * 流程权限服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 14:28
 */
public interface ProcessPermissionService {

    /**
     * 是否admin用户
     *
     * @param groupIds 用户组
     * @return {@link boolean}
     * @author Blare
     */
    boolean isAdmin(Set<String> groupIds);

    /**
     * 流程启动权限
     *
     * @param userId              用户ID
     * @param groups              用户组
     * @param processDefinitionId 流程定义ID
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasStartupPermOnDefinition(String userId, Set<String> groups, String processDefinitionId);

    /**
     * 获取权限启动组列表
     *
     * @param identityLinks 身份信息
     * @return {@link List <String>}
     * @author Blare
     */
    List<String> getAuthStarterGroupIds(List<IdentityLink> identityLinks);

    /**
     * 获取权限启动人列表
     *
     * @param identityLinks 身份信息
     * @return {@link List<String>}
     * @author Blare
     */
    List<String> getAuthStarterUserIds(List<IdentityLink> identityLinks);

    /**
     * 判断用户是否是任务的所有者或者执行人
     *
     * @param userId 用户ID
     * @param task   任务对象
     * @return {@link boolean}
     * @author Blare
     */
    boolean isTaskOwnerOrAssignee(String userId, Task task);

    /**
     * 判断任务是否委托状态
     *
     * @param task 任务对象
     * @return {@link boolean}
     * @author Blare
     */
    boolean isDelegateOfTask(Task task);

    /**
     * 判断是否有任务按钮权限
     *
     * @param task       任务对象
     * @param buttonType 按钮
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasTaskButtonPerm(Task task, ButtonType buttonType);

    /**
     * 判断是否有任务读取权限
     *
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasReadPermOnTask(String taskId, String userId, Set<String> groupIds, boolean validateReadProcessInstance, boolean validateReadParentTask);

    /**
     * 校验用户是否有权限读取历史任务
     *
     * @param taskId                      任务ID
     * @param userId                      用户ID
     * @param groupIds                    用户组
     * @param validateReadProcessInstance 是否验证实例权限
     * @param validateReadParentTask      是否验证父级任务权限
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasReadPermOnHistoricTask(String taskId, String userId, Set<String> groupIds, boolean validateReadProcessInstance, boolean validateReadParentTask);

    /**
     * 判断是否有任务执行权限
     *
     * @param task     任务
     * @param userId   用户ID
     * @param groupIds 用户组
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasExecPermOnTask(Task task, String userId, Set<String> groupIds);

    /**
     * 判断是否有任务委派权限
     *
     * @param task      任务
     * @param userId    用户ID
     * @param groupIds  用户组
     * @param delegator 委派人
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasDelegatePermOnTask(Task task, String userId, Set<String> groupIds, String delegator);

    /**
     * 判断是否有任务转办权限
     *
     * @param task     任务
     * @param userId   用户ID
     * @param groupIds 用户组
     * @param assignee 转办人
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasAssignPermOnTask(Task task, String userId, Set<String> groupIds, String assignee);

    /**
     * 判断用户是否有权限读取该流程实例
     *
     * @param userId            用户ID
     * @param groupIds          用户组
     * @param processInstanceId 流程实例ID
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasReadPermOnProcessInstance(String userId, Set<String> groupIds, String processInstanceId);

    /**
     * 判断用户是否有权限读取该流程实例
     *
     * @param userId                  用户ID
     * @param groupIds                用户组
     * @param historicProcessInstance 历史流程实例
     * @param processInstanceId       流程实例ID
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasReadPermOnProcessInstance(String userId, Set<String> groupIds, HistoricProcessInstance historicProcessInstance, String processInstanceId);

    /**
     * 判断是否有设置候选人权限
     *
     * @param userId   用户ID
     * @param groupIds 用户组
     * @param taskId   任务ID
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasSetCandidatePermOnTask(String userId, Set<String> groupIds, String taskId);

    /**
     * 判断是否有流程实例操作权限
     *
     * @param userId            用户ID
     * @param groupIds          用户组
     * @param processInstanceId 流程实例ID
     * @return {@link boolean}
     * @author Blare
     */
    boolean hasExecPermOnProcInstance(String userId, Set<String> groupIds, String processInstanceId);
}