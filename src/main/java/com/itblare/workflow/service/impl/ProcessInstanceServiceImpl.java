package com.itblare.workflow.service.impl;

import com.itblare.workflow.service.PermissionService;
import com.itblare.workflow.service.ProcessInstanceService;
import com.itblare.workflow.service.ProcessTaskService;
import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.enumerate.CommentType;
import com.itblare.workflow.support.flowcmd.AddCcIdentityLinkCmd;
import com.itblare.workflow.support.model.req.InstanceCommonDto;
import com.itblare.workflow.support.model.req.InstanceDelDto;
import com.itblare.workflow.support.model.req.InstanceStartDto;
import com.itblare.workflow.support.utils.CommonUtil;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 流程实例服务实现
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/11 1:33
 */
@Service
public class ProcessInstanceServiceImpl extends FlowableFactory implements ProcessInstanceService {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ProcessTaskService processTaskService;

    @Override
    public String start(InstanceStartDto instanceStartDto) {
        // String processDefinitionId = CommonUtil.trimToEmptyStr(instanceStartDto.getProcessDefinitionId());
        String processDefinitionId = instanceStartDto.getProcessDefinitionId().trim();
        String processDefinitionKey = instanceStartDto.getProcessDefinitionKey().trim();
        if (processDefinitionId.length() == 0 && processDefinitionKey.length() == 0) {
            throw new FlowableException("request param both processDefinitionId and processDefinitionKey is not found");
        } else if (processDefinitionId.length() != 0 && processDefinitionKey.length() != 0) {
            throw new FlowableException("request param both processDefinitionId and processDefinitionKey is found");
        }
        String userId = instanceStartDto.getUserId();

        ProcessDefinition definition = permissionService.validateReadPermissionOnProcessDefinition(userId, processDefinitionId, processDefinitionKey, instanceStartDto.getTenantId());
        Map<String, Object> startVariables = null;
        if (instanceStartDto.getVariables() != null && !instanceStartDto.getVariables().isEmpty()) {
            startVariables = instanceStartDto.getVariables();
            // 默认设置流程启动人变量 __initiator__
            startVariables.put(FlowableConstant.INITIATOR, userId);
        } else {
            startVariables = new HashMap<>(1);
            // 默认设置流程启动人变量 __initiator__
            startVariables.put(FlowableConstant.INITIATOR, userId);
        }

        Authentication.setAuthenticatedUserId(userId);

        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        processInstanceBuilder.processDefinitionId(definition.getId());
        // 流程实例标题 UserRealName
        processInstanceBuilder.name(instanceStartDto.getUserId() + definition.getName());
        // 业务key
        processInstanceBuilder.businessKey(instanceStartDto.getBusinessKey());
        processInstanceBuilder.variables(startVariables);

        ProcessInstance instance = processInstanceBuilder.start();
        String processInstanceId = instance.getProcessInstanceId();
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        for (Task task : tasks) {
            // 约定：发起者节点为 __initiator__ ,则自动完成任务
            if (FlowableConstant.INITIATOR.equals(task.getTaskDefinitionKey())) {
                processTaskService.addComment(task.getId(), processInstanceId, userId, CommentType.TJ.getName(), null);
                if (Objects.nonNull(task.getAssignee()) && !task.getAssignee().isEmpty()) {
                    taskService.setAssignee(task.getId(), userId);
                }
                taskService.complete(task.getId());
                if (!CommonUtil.isEmptyCollection(instanceStartDto.getCcTotoList())) {
                    managementService.executeCommand(new AddCcIdentityLinkCmd(processInstanceId, task.getId(), userId, instanceStartDto.getCcTotoList()));
                }
            }
        }
        return processInstanceId;
    }

    @Override
    public void setAuthenticatedUserId(String userId) {

    }

    @Override
    public boolean suspended(InstanceCommonDto instanceCommonDto) {
        return false;
    }

    @Override
    public void activate(InstanceCommonDto instanceCommonDto) {

    }

    @Override
    public void delete(InstanceDelDto instanceDelDto) {

    }

    @Override
    public ProcessInstance getById(String processInstanceId) {
        return null;
    }

    @Override
    public HistoricProcessInstance getHistoricById(String processInstanceId) {
        return null;
    }
}