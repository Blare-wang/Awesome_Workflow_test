package com.itblare.workflow.support.flowcmd;

import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.repository.ProcessDefinition;

import java.io.Serializable;
import java.util.Objects;

/**
 * CMD方式查询流程定义
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/9/24 18:04
 */
public class GetProcessDefinitionInfoCmd implements Command<ProcessDefinition>, Serializable {

    private static final long serialVersionUID = 6375557445458942986L;
    protected String processDefinitionId;
    protected String processDefinitionKey;
    protected String tenantId;

    public GetProcessDefinitionInfoCmd(String processDefinitionId, String processDefinitionKey, String tenantId) {
        this.processDefinitionId = processDefinitionId;
        this.processDefinitionKey = processDefinitionKey;
        this.tenantId = tenantId;
    }

    @Override
    public ProcessDefinition execute(CommandContext commandContext) {
        ProcessEngineConfigurationImpl processEngineConfiguration = CommandContextUtil.getProcessEngineConfiguration(commandContext);
        ProcessDefinitionEntityManager processDefinitionEntityManager = processEngineConfiguration.getProcessDefinitionEntityManager();
        // Find the process definition
        ProcessDefinition processDefinition;
        if (processDefinitionId != null) {
            processDefinition = processDefinitionEntityManager.findById(processDefinitionId);
            if (processDefinition == null) {
                throw new FlowableObjectNotFoundException("流程定义【id：" + processDefinitionId + "】不存在！", ProcessDefinition.class);
            }
        } else if (processDefinitionKey != null && isEmptyStr(tenantId)) {
            processDefinition = processDefinitionEntityManager.findLatestProcessDefinitionByKey(processDefinitionKey);
            if (processDefinition == null) {
                throw new FlowableObjectNotFoundException("流程定义【key：" + processDefinitionKey + "】不存在", ProcessDefinition.class);
            }
        } else if (processDefinitionKey != null && isNotEmptyStr(tenantId)) {
            processDefinition = processDefinitionEntityManager.findLatestProcessDefinitionByKeyAndTenantId(processDefinitionKey, tenantId);
            if (processDefinition == null) {
                if (processEngineConfiguration.isFallbackToDefaultTenant()) {
                    String defaultTenantValue = processEngineConfiguration.getDefaultTenantProvider().getDefaultTenant(null, null, null);
                    if (StringUtils.isNotEmpty(defaultTenantValue)) {
                        processDefinition = processDefinitionEntityManager.findLatestProcessDefinitionByKeyAndTenantId(processDefinitionKey, defaultTenantValue);
                    } else {
                        processDefinition = processDefinitionEntityManager.findLatestProcessDefinitionByKey(processDefinitionKey);
                    }
                    if (Objects.isNull(processDefinition)) {
                        throw new FlowableObjectNotFoundException("流程定义【key：" + processDefinitionKey + "】不存在. 回退到默认租户也被应用！", ProcessDefinition.class);
                    }
                } else {
                    throw new FlowableObjectNotFoundException("流程定义【key：" + processDefinitionKey + " and tenantId： '" + tenantId + "】不存在！", ProcessDefinition.class);
                }
            }
        } else {
            throw new FlowableIllegalArgumentException("流程定义ID或KEY不能为空！");
        }
        return processDefinition;
    }

    private boolean isNotEmptyStr(String tenantId) {
        return Objects.nonNull(tenantId) && !tenantId.isEmpty();
    }

    private boolean isEmptyStr(String tenantId) {
        return Objects.isNull(tenantId) || tenantId.isEmpty();
    }


}