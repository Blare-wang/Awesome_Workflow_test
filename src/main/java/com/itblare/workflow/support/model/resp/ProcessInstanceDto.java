package com.itblare.workflow.support.model.resp;

import java.util.List;
import java.util.Map;

/**
 * 流程实例信息DTO
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/16 10:59
 */
public class ProcessInstanceDto {

    /**
     * 流程定义ID
     */
    protected String processDefinitionId;

    /**
     * 流程定义KEY
     */
    private String processDefinitionKey;

    /**
     * 流程定义名称
     */
    private String processDefinitionName;

    /**
     * 流程定义分类
     */
    protected String processDefinitionCategory;

    /**
     * 流程定义版本
     */
    protected int processDefinitionVersion;

    /**
     * 流程定义部署ID
     */
    protected String processDefinitionDeploymentId;

    /**
     * 流程定义描述
     */
    private String processDefinitionDescription;

    /**
     * 业务系统标识ID
     */
    private String tenantId;

    /**
     * 业务标识KEY
     */
    private String businessKey;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 流程实例名称
     */
    private String processInstanceName;

    /**
     * 流程实例状态
     */
    // private InstanceStatus instanceStatus;

    /**
     * 流程实例删除原由
     */
    private String deleteReason;

    /**
     * 流程实例变量
     */
    private Map<String, Object> params;

    /**
     * 流程实例当前激活任务
     */
    private List<TaskDto> taskList;

    /**
     * 流程实例启动者
     */
    private String processInstanceStartUserId;

    /**
     * 流程实例是否是运行中并挂起
     */
    private Boolean suspended;

    /**
     * 流程实例是否是运行中并被结束
     */
    private Boolean ended;

    /**
     * 流程实例是启动人
     */
    private String startUserId;
}
