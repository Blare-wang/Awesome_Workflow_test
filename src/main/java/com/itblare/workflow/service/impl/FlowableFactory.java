package com.itblare.workflow.service.impl;

import org.flowable.engine.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Flowable 服务工厂
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/14 11:08
 */
@SuppressWarnings("all")
public class FlowableFactory {

    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected IdentityService identityService;
    @Autowired
    protected ManagementService managementService;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected FormService formService;
}