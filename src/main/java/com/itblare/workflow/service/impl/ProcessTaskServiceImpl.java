package com.itblare.workflow.service.impl;

import com.itblare.workflow.entity.CommentEntityDto;
import com.itblare.workflow.service.PermissionService;
import com.itblare.workflow.service.ProcessTaskService;
import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.enumerate.CommentType;
import com.itblare.workflow.support.exceptions.FlowableNoPermissionException;
import com.itblare.workflow.support.flowcmd.AddCcIdentityLinkCmd;
import com.itblare.workflow.support.flowcmd.CompleteTaskReadCmd;
import com.itblare.workflow.support.model.req.*;
import com.itblare.workflow.support.utils.CommonUtil;
import com.itblare.workflow.support.utils.FlowableUtil;
import org.flowable.bpmn.model.UserTask;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.task.Comment;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.identitylink.api.IdentityLinkType;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 流程任务服务实现
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/11 0:52
 */
@Service
public class ProcessTaskServiceImpl extends FlowableFactory implements ProcessTaskService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public void complete(TaskExecDto taskExecDto) {
        String taskId = taskExecDto.getTaskId();
        String currUserId = taskExecDto.getUserId();
        Task task = getTaskNotNull(taskId);
        if (!permissionService.isTaskOfOwnerOrAssignee(currUserId, task)) {
            if (CommonUtil.isEmptyAfterStrip(task.getScopeType()) && !permissionService.validateIfUserIsInitiatorAndCanCompleteTask(currUserId, task)) {
                throw new FlowableNoPermissionException("用户无权限!");
            }
        }

        Map<String, Object> completeVariables = null;
        if (taskExecDto.getValues() != null && !taskExecDto.getValues().isEmpty()) {
            completeVariables = taskExecDto.getValues();
            // 允许任务表单修改流程表单场景 begin
            // 与前端约定：流程表单变量名为 processInstanceFormData，且只有流程表单startFormKey=taskFormKey时才允许修改该变量的值，防止恶意节点修改流程表单内容
            if (completeVariables.containsKey(FlowableConstant.PROCESS_INSTANCE_FORM_DATA)) {
                String startFormKey = formService.getStartFormKey(task.getProcessDefinitionId());
                String taskFormKey = formService.getTaskFormKey(task.getProcessDefinitionId(),
                        task.getTaskDefinitionKey());
                boolean modifyProcessInstanceFormData = CommonUtil.isEmptyAfterStrip(startFormKey) && CommonUtil.isEmptyAfterStrip(taskFormKey) && startFormKey.equals(taskFormKey);
                if (!modifyProcessInstanceFormData) {
                    throw new FlowableNoPermissionException("用户无权限!");
                }
            }
            // 允许任务表单修改流程表单场景 end
            // 非会签用户节点，默认设置流程变量 __taskDefinitionKey__=currUserId，用于存储该节点执行人，且以最近的执行人为准
            UserTask userTask = (UserTask) FlowableUtil.getFlowElement(repositoryService,
                    task.getProcessDefinitionId(), task.getTaskDefinitionKey());
            if (userTask != null && !userTask.hasMultiInstanceLoopCharacteristics()) {
                completeVariables.put("__" + task.getTaskDefinitionKey() + "__", currUserId);
            }
        } else {
            // 非会签用户节点，默认设置流程变量 __taskDefinitionKey__=currUserId，用于存储该节点执行人，且以最近的执行人为准
            UserTask userTask = (UserTask) FlowableUtil.getFlowElement(repositoryService,
                    task.getProcessDefinitionId(), task.getTaskDefinitionKey());
            if (userTask != null && !userTask.hasMultiInstanceLoopCharacteristics()) {
                completeVariables = new HashMap<>(1);
                completeVariables.put("__" + task.getTaskDefinitionKey() + "__", currUserId);
            }
        }
        // 添加批注
        this.addComment(taskId,
                task.getProcessInstanceId(),
                currUserId,
                FlowableConstant.INITIATOR.equals(task.getTaskDefinitionKey()) ? CommentType.CXTJ.getName() : CommentType.WC.getName(), taskExecDto.getCommentDto().getMessage());

        // 处理抄送
        if (Objects.nonNull(taskExecDto.getCcTotoList()) && taskExecDto.getCcTotoList().size() > 0) {
            managementService.executeCommand(new AddCcIdentityLinkCmd(task.getProcessInstanceId(), task.getId(), currUserId, taskExecDto.getCcTotoList()));

        }

        if (task.getAssignee() == null || !task.getAssignee().equals(currUserId)) {
            taskService.setAssignee(taskId, currUserId);
        }
        // 判断是否是协办完成还是正常流转
        if (permissionService.isPendingTask(task)) {
            taskService.resolveTask(taskId, completeVariables);
            // 如果当前执行人是任务所有人，直接完成任务
            if (currUserId.equals(task.getOwner())) {
                taskService.complete(taskId, completeVariables);
            }
        } else {
            taskService.complete(taskId, completeVariables);
        }
    }

    @Override
    public void claim(TaskCommonDto taskClaimDto) {

    }

    @Override
    public void unClaim(TaskCommonDto taskClaimDto) {

    }

    @Override
    public void delegate(TaskDelegateDto taskDelegateDto) {

    }

    @Override
    public void resolve(Task task, String userId, Map<String, Object> variables) {

    }

    @Override
    public void setAssignee(TaskAssigneeDto taskAssigneeDto) {

    }

    @Override
    public void addTypeComment(String taskId, String processInstanceId, String userId, CommentType commentType, String message) {
        Authentication.setAuthenticatedUserId(userId);
        commentType = Objects.isNull(commentType) ? CommentType.SP : commentType;
        message = (message == null || message.length() == 0) ? commentType.getName() : message;
        taskService.addComment(taskId, processInstanceId, commentType.toString(), message);
    }

    @Override
    public void addComment(String taskId, String processInstanceId, String userId, String message, String fullMessage) {

    }

    @Override
    public void addAttachment(String taskId, String processInstanceId, String attachmentName, String attachmentDesc, String url) {

    }

    @Override
    public void withdraw(TaskWithDrawDto taskWithDrawDto) {


    }

    @Override
    public void delete(String taskId, String reason, boolean cascade, boolean isRunning) {

    }

    @Override
    public void read(String userId, List<String> taskIds) {
        if (taskIds == null || taskIds.size() == 0) {
            throw new FlowableException("taskIds is null or empty");
        }
        for (String taskId : taskIds) {
            managementService.executeCommand(new CompleteTaskReadCmd(taskId, userId));
        }
    }

    @Override
    public void update(TaskUpdateDto taskUpdateDto) {

    }

    @Override
    public void addInvolveUser(String taskId, String userId, Set<String> groupIds, String involveUserId) {
        final Task task = getTaskNotNull(taskId);
        permissionService.validateIfUserIsInitiatorAndCanCompleteTask(userId, task);
        if (CommonUtil.isNotEmptyAfterStrip(involveUserId)) {
            taskService.addUserIdentityLink(taskId, involveUserId, IdentityLinkType.PARTICIPANT);
        }
        if (!CommonUtil.isEmptyCollection(groupIds)) {
            groupIds.forEach(g -> {
                taskService.addGroupIdentityLink(taskId, g, IdentityLinkType.PARTICIPANT);
            });
        }
    }

    @Override
    public void removeInvolvedUser(String taskId, String userId, Set<String> groupIds, String involveUserId) {
        Task task = getTaskNotNull(taskId);
        permissionService.validateReadPermissionOnTask(task.getId(), userId, false, false);
        if (CommonUtil.isNotEmptyAfterStrip(involveUserId)) {
            taskService.deleteUserIdentityLink(taskId, involveUserId, IdentityLinkType.PARTICIPANT);
        }
        if (!CommonUtil.isEmptyCollection(groupIds)) {
            groupIds.forEach(g -> {
                taskService.deleteGroupIdentityLink(taskId, g, IdentityLinkType.PARTICIPANT);
            });
        }
    }

    @Override
    public void addIdentityLink(IdentityDto identityDto) {

    }

    @Override
    public void deleteIdentityLink(IdentityDto identityDto) {

    }

    @Override
    public void stopProcessInstance(TaskCommonDto taskParamDto) {

    }

    @Override
    public void addCandidate(String userId, Set<String> groupIds, String taskId, String candidateUser, String candidateGroup) {

    }

    @Override
    public Task getTaskNotNull(String taskId) {
        final Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (Objects.isNull(task)) {
            throw new FlowableObjectNotFoundException("任务【id："+taskId+"】不存在！");
        }
        return task;
    }

    @Override
    public List<Task> getSubTasks(String taskId, String userId, Set<String> groupIds) {
        return null;
    }

    @Override
    public HistoricTaskInstance getHistoricTaskInstanceNotNull(String taskId) {
        final HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if (Objects.isNull(historicTaskInstance)) {
            throw new FlowableObjectNotFoundException("任务【id："+taskId+"】不存在！");
        }
        return historicTaskInstance;
    }

    @Override
    public List<Comment> getComments(String taskId, String processInstanceId, String type, String userId) {
        return null;
    }

    @Override
    public List<CommentEntityDto> getComments(String processInstanceId, String taskId, String action, String type, String userId) {
        return null;
    }

    @Override
    public List<CommentEntityDto> getCommentsByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<CommentEntityDto> getCommentsByProcessInstanceId(String processInstanceId) {
        return null;
    }
}