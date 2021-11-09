package com.itblare.workflow.service.impl;

import com.itblare.workflow.service.ProcessDefinitionService;
import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.flowcmd.DeployModelCmd;
import com.itblare.workflow.support.flowcmd.GetProcessDefinitionInfoCmd;
import com.itblare.workflow.support.flowcmd.SavedModelEditorCmd;
import com.itblare.workflow.support.model.req.IdentityDto;
import com.itblare.workflow.support.model.req.ProcessDefinitionDto;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.job.api.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 流程定义服务实现
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 15:35
 */
@Service
public class ProcessDefinitionServiceImpl extends FlowableFactory implements ProcessDefinitionService {

    @Override
    @Transactional
    public void addDeploy(String key, String name, String category, String description, String tenantId, MultipartFile file) {

        if (Objects.isNull(file) || file.isEmpty()) {
            throw new IllegalArgumentException("部署文件为空！");
        }
        String filename = file.getOriginalFilename();
        if (Objects.isNull(filename) || filename.isBlank()) {
            throw new IllegalArgumentException("部署文件文件名为空！");
        }
        boolean isBpmnFile = filename.endsWith(".bpmn20.xml") || filename.endsWith(".bpmn");
        boolean isBpmnPackageFile = filename.endsWith(".bar") || filename.endsWith(".zip");
        if (!isBpmnFile && !isBpmnPackageFile) {
            throw new IllegalArgumentException("部署文件必须是以.bpmn20.xml或.bpmn或.bar或.zip结尾！");
        }

        if (isBpmnFile) {
            try {
                // 保存模型
                String modelId = managementService.executeCommand(new SavedModelEditorCmd("2",
                        null,
                        key,
                        name,
                        category,
                        null,
                        tenantId,
                        file.getBytes()));
                // 模型部署
                managementService.executeCommand(new DeployModelCmd(modelId));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
                ZipEntry entry = zipInputStream.getNextEntry();
                while (entry != null) {
                    if (!entry.isDirectory()) {
                        String entryName = entry.getName();
                        byte[] bytes = IoUtil.readInputStream(zipInputStream, entryName);
                        // 保存模型
                        String modelId = managementService.executeCommand(new SavedModelEditorCmd("2",
                                null,
                                key,
                                name,
                                category,
                                null,
                                tenantId, bytes));
                        // 模型部署
                        managementService.executeCommand(new DeployModelCmd(modelId));
                    }
                    entry = zipInputStream.getNextEntry();
                }
            } catch (Exception e) {
                throw new FlowableException("读取压缩包文件流失败", e);
            }
        }

    }

    @Override
    @Transactional
    public void delete(String processDefinitionId, Boolean cascade) {
        ProcessDefinition processDefinition = getNonNullById(processDefinitionId);
        if (Objects.isNull(processDefinition.getDeploymentId())) {
            throw new IllegalArgumentException("流程定义【" + processDefinitionId + "】尚未部署！");
        }
        if (cascade) {
            // 删除定时任务
            List<Job> jobs = managementService.createTimerJobQuery().processDefinitionId(processDefinitionId).list();
            if (Objects.nonNull(jobs) && jobs.size() > 0) {
                jobs.forEach(job -> managementService.deleteTimerJob(job.getId()));
            }
            // 删除部署
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        } else {
            // 运行中实例数量
            long processCount = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).count();
            if (processCount > 0) {
                throw new FlowableException("流程定义【" + processDefinition.getId() + "】下有正在运行中的实例！");
            }
            long jobCount = managementService.createTimerJobQuery().processDefinitionId(processDefinition.getId()).count();
            if (jobCount > 0) {
                throw new FlowableException("流程定义【" + processDefinition.getId() + "】下有正在运行中的定时任务！");
            }
            repositoryService.deleteDeployment(processDefinition.getDeploymentId());
        }

    }

    @Override
    @Transactional
    public void activate(ProcessDefinitionDto processDefinitionDto) {
        String processDefinitionId = processDefinitionDto.getProcessDefinitionId();
        ProcessDefinition processDefinition = getNonNullById(processDefinitionId);
        if (!processDefinition.isSuspended()) {
            throw new FlowableException("流程定义【" + processDefinitionId + "】尚未被挂起！");
        }
        repositoryService.activateProcessDefinitionById(processDefinitionId,
                processDefinitionDto.isIncludeProcessInstances(),
                Date.from(processDefinitionDto.getDate().atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    @Transactional
    public void suspend(ProcessDefinitionDto processDefinitionDto) {
        String processDefinitionId = processDefinitionDto.getProcessDefinitionId();
        ProcessDefinition processDefinition = getNonNullById(processDefinitionId);
        if (processDefinition.isSuspended()) {
            throw new FlowableException("流程定义【" + processDefinitionId + "】已经被挂起！");
        }
        repositoryService.suspendProcessDefinitionById(processDefinitionId,
                processDefinitionDto.isIncludeProcessInstances(),
                Date.from(processDefinitionDto.getDate().atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    @Transactional
    public void addIdentityLink(IdentityDto identityDto) {
        int type = identityDto.getIdentityType();
        if (FlowableConstant.IDENTITY_GROUP != type && FlowableConstant.IDENTITY_USER != type) {
            throw new FlowableException("身份类型必须是 " + FlowableConstant.IDENTITY_GROUP + " 或 " + FlowableConstant.IDENTITY_USER);
        }
        Set<String> identityIds = identityDto.getIdentityId();
        if (Objects.isNull(identityIds) || identityIds.size() == 0) {
            throw new FlowableException("身份信息ID不能为空！");
        }
        ProcessDefinition processDefinition = getNonNullById(identityDto.getProcessDefinitionId());
        if (FlowableConstant.IDENTITY_GROUP == type) {
            identityIds.forEach(identityId -> repositoryService.addCandidateStarterGroup(processDefinition.getId(), identityId));
        } else //noinspection ConstantConditions
            if (FlowableConstant.IDENTITY_USER == type) {
                identityIds.forEach(identityId -> repositoryService.addCandidateStarterUser(processDefinition.getId(), identityId));
            }
    }

    @Override
    @Transactional
    public void deleteIdentityLink(IdentityDto identityDto) {
        Set<String> identityIds = identityDto.getIdentityId();
        int type = identityDto.getIdentityType();
        if (Objects.isNull(identityIds) || identityIds.size() == 0) {
            throw new FlowableException("身份信息ID不能为空！");
        }
        if (FlowableConstant.IDENTITY_GROUP != type && FlowableConstant.IDENTITY_USER != type) {
            throw new FlowableException("身份类型必须是 " + FlowableConstant.IDENTITY_GROUP + " 或 " + FlowableConstant.IDENTITY_USER);
        }
        ProcessDefinition processDefinition = getNonNullById(identityDto.getProcessDefinitionId());
        if (FlowableConstant.IDENTITY_GROUP == type) {
            identityIds.forEach(identityId -> repositoryService.deleteCandidateStarterGroup(processDefinition.getId(), identityId));
        } else //noinspection ConstantConditions
            if (FlowableConstant.IDENTITY_USER == type) {
                identityIds.forEach(identityId -> repositoryService.deleteCandidateStarterUser(processDefinition.getId(), identityId));
            }
    }

    @Override
    public ProcessDefinition getById(String processDefinitionId) {
        return managementService.executeCommand(new GetProcessDefinitionInfoCmd(processDefinitionId, null, null));
    }

    public ProcessDefinition getNonNullById(String processDefinitionId) {
        ProcessDefinition processDefinition = getById(processDefinitionId);
        if (Objects.isNull(processDefinition)) {
            throw new FlowableObjectNotFoundException("流程定义不存在", ProcessDefinition.class);
        }
        return processDefinition;
    }
}