package com.itblare.workflow.service;

import com.itblare.workflow.entity.CommentEntityDto;

import com.itblare.workflow.support.model.req.*;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import com.itblare.workflow.support.enumerate.CommentType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 流程任务服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 14:26
 */
public interface ProcessTaskService {

    /**
     * 完成任务
     *
     * @param taskExecDto 任务参数
     * @author Blare
     */
    void complete(TaskExecDto taskExecDto);

    /**
     * 任务签收
     *
     * @param taskClaimDto 任务参数
     * @author Blare
     */
    void claim(TaskCommonDto taskClaimDto);

    /**
     * 任务反签收
     *
     * @param taskClaimDto 任务参数
     * @author Blare
     */
    void unClaim(TaskCommonDto taskClaimDto);

    /**
     * 任务委派
     *
     * @param taskDelegateDto 任务参数
     * @author Blare
     */
    void delegate(TaskDelegateDto taskDelegateDto);

    /**
     * 任务归还
     *
     * @param task      任务
     * @param userId    用户ID
     * @param variables 流程变量
     * @author Blare
     */
    void resolve(Task task, String userId, Map<String, Object> variables);

    /**
     * 任务转办
     *
     * @param taskAssigneeDto 任务参数
     * @author Blare
     */
    void setAssignee(TaskAssigneeDto taskAssigneeDto);

    /**
     * 任务类型批注
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param userId            用户ID
     * @param commentType       执行批注类型
     * @param message           批注内容
     * @author Blare
     */
    void addTypeComment(String taskId, String processInstanceId, String userId, CommentType commentType, String message);

    /**
     * 用户任务批注
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param userId            用户ID
     * @param message           批注信息
     * @param fullMessage       长批注信息
     * @author Blare
     */
    void addComment(String taskId, String processInstanceId, String userId, String message, String fullMessage);

    /**
     * 任务附件(不存储具体附件信息)
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param attachmentName    附件名称
     * @param attachmentDesc    附件描述
     * @param url               附件链接
     * @author Blare
     */
    void addAttachment(String taskId, String processInstanceId, String attachmentName, String attachmentDesc, String url);

    /**
     * 任务回退
     *
     * @param taskWithDrawDto 任务参数
     * @author Blare
     */
    void withdraw(TaskWithDrawDto taskWithDrawDto);

    /**
     * 任务删除
     *
     * @param taskId    任务的id，不能为null
     * @param cascade   如果级联为真，则与此任务相关的历史信息也将被删除
     * @param isRunning 是否删除运行中
     * @author Blare
     */
    void delete(String taskId, String reason, boolean cascade, boolean isRunning);

    /**
     * 任务阅览
     *
     * @param userId  用户ID
     * @param taskIds 任务列表
     * @author Blare
     */
    void read(String userId, List<String> taskIds);

    /**
     * 修改任务
     *
     * @param taskUpdateDto 任务修改参数
     * @author Blare
     */
    void update(TaskUpdateDto taskUpdateDto);

    /**
     * 新增任务参与人
     *
     * @param taskId        任务ID
     * @param userId        用户ID
     * @param groupIds      参与用户组
     * @param involveUserId 参与人用户ID
     * @author Blare
     */
    void addInvolveUser(String taskId, String userId, Set<String> groupIds, String involveUserId);

    /**
     * 移除任务参与人
     *
     * @param taskId        任务ID
     * @param userId        用户ID
     * @param groupIds      用户组
     * @param involveUserId 参与人用户ID
     * @author Blare
     */
    void removeInvolvedUser(String taskId, String userId, Set<String> groupIds, String involveUserId);

    /**
     * 新增任务关联人
     *
     * @param identityDto 任务授权信息
     * @author Blare
     */
    void addIdentityLink(IdentityDto identityDto);

    /**
     * 移除任务关联人
     *
     * @param identityDto 任务授权信息
     * @author Blare
     */
    void deleteIdentityLink(IdentityDto identityDto);

    /**
     * 终止流程
     *
     * @param taskParamDto 任务参数
     * @author Blare
     */
    void stopProcessInstance(TaskCommonDto taskParamDto);

    /**
     * 给任务设置候选人（组）
     *
     * @param userId         当前用户ID
     * @param groupIds       当前用户组
     * @param taskId         用户ID
     * @param candidateUser  候选人
     * @param candidateGroup 候选组
     * @author Blare
     */
    void addCandidate(String userId, Set<String> groupIds, String taskId, String candidateUser, String candidateGroup);

    /**
     * 获取非空任务(runtime)
     *
     * @param taskId 任务ID
     * @return {@link Task}
     * @author Blare
     */
    Task getTaskNotNull(String taskId);

    /**
     * 查询子任务列表
     *
     * @param taskId 任务ID
     * @param userId 用户ID
     * @return {@link List<Task>}
     * @author Blare
     */
    List<Task> getSubTasks(String taskId, String userId, Set<String> groupIds);

    /**
     * 查询历史任务详情
     *
     * @param taskId 任务ID
     * @return {@link HistoricTaskInstance}
     * @author Blare
     */
    HistoricTaskInstance getHistoricTaskInstanceNotNull(String taskId);

    /**
     * 查询任务(流程实例)批注列表
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param type              批注类型
     * @param userId            用户ID
     * @return {@link List<Comment>}
     * @author Blare
     */
    List<Comment> getComments(String taskId, String processInstanceId, String type, String userId);

    /**
     * 查询任务(流程实例)批注列表
     *
     * @param taskId            任务ID
     * @param processInstanceId 流程实例ID
     * @param action            操作
     * @param type              批注类型
     * @param userId            用户ID
     * @return {@link List<Comment>}
     * @author Blare
     */
    List<CommentEntityDto> getComments(String processInstanceId, String taskId, String action, String type, String userId);

    /**
     * 获取流程实例批注信息
     *
     * @param taskId 流程任务ID
     * @return {@link List<CommentEntityDto>}
     * @author Blare
     */
    List<CommentEntityDto> getCommentsByTaskId(String taskId);

    /**
     * 获取流程实例批注信息
     *
     * @param processInstanceId 流程实例ID
     * @return {@link List<CommentEntityDto>}
     * @author Blare
     */
    List<CommentEntityDto> getCommentsByProcessInstanceId(String processInstanceId);
}