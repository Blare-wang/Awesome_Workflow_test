package com.itblare.workflow.service;

import com.itblare.workflow.support.model.req.InstanceCommonDto;
import com.itblare.workflow.support.model.req.InstanceDelDto;
import com.itblare.workflow.support.model.req.InstanceStartDto;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;

/**
 * 流程实例服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 14:26
 */
public interface ProcessInstanceService {

    /**
     * 开启实例
     *
     * @param instanceStartDto 实例启动参数
     * @return {@link String}
     * @author Blare
     */
    String start(InstanceStartDto instanceStartDto);

    /**
     * 设置发起人【需要启动流程之前调用，并且流程启动完毕后置为null】
     *
     * @param userId 用户ID
     * @author Blare
     */
    void setAuthenticatedUserId(String userId);

    /**
     * 挂起实例
     *
     * @param instanceCommonDto 流程实例信息
     * @return {@link boolean}
     * @author Blare
     */
    boolean suspended(InstanceCommonDto instanceCommonDto);

    /**
     * 激活实例
     *
     * @param instanceCommonDto 流程实例ID
     * @author Blare
     */
    void activate(InstanceCommonDto instanceCommonDto);

    /**
     * 删除流程实例
     *
     * @param instanceDelDto 流程实例删除
     * @author Blare
     */
    void delete(InstanceDelDto instanceDelDto);

    /**
     * 查询单一流程实例
     *
     * @param processInstanceId 流程实例ID
     * @return {@link ProcessInstance}
     * @author Blare
     */
    ProcessInstance getById(String processInstanceId);

    /**
     * 查询单一历史流程实例
     *
     * @param processInstanceId 流程实例ID
     * @return {@link HistoricProcessInstance}
     * @author Blare
     */
    HistoricProcessInstance getHistoricById(String processInstanceId);
}