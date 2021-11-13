package com.itblare.workflow.api;

import com.itblare.workflow.support.constant.FlowableConstant;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.flowable.task.service.impl.HistoricTaskInstanceQueryProperty;
import org.flowable.task.service.impl.TaskQueryProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 我的流程
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/14 0:16
 */
@RestController
public class OwerFlowApi {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    // 我的流程定义
    public Object listMyself() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionNameLike("%ProcessDefinitionName%");
        processDefinitionQuery.latestVersion().active().startableByUser("userId");
        List<ProcessDefinition> list = processDefinitionQuery.list();
        final long count = processDefinitionQuery.count();
        final List<ProcessDefinition> list2 = processDefinitionQuery.listPage(1, 10);
        return null;
    }

    // 我的已办理
    public Object listDone() {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        query.finished().or().taskAssignee("userId").taskOwner("userId").endOr();
        query.orderBy(HistoricTaskInstanceQueryProperty.START).desc();
        final List<HistoricTaskInstance> list1 = query.list();// 已办列表
        final long count = query.count();// 已办数量
        final List<HistoricTaskInstance> list2 = query.listPage(0, 10);// 已办分页列表
        return null;
    }

    // 我的待办列表
    public Object listTodo() {
        TaskQuery query = taskService.createTaskQuery();
        query.taskCategory(FlowableConstant.CATEGORY_TODO); // FlowableEventListener 监听器处设置分类
        query.orderBy(TaskQueryProperty.CREATE_TIME).desc();
        query.or().taskAssignee("userId").taskOwner("userId").endOr();
        final List<Task> list1 = query.list();
        final long count = query.count();
        final List<Task> tasks = query.listPage(1, 10);
        return null;
    }

    // 我的待阅列表
    public Object listToRead() {
        TaskQuery query = taskService.createTaskQuery();
        query.taskCategory(FlowableConstant.CATEGORY_TO_READ);
        query.or().taskAssignee("userId").taskOwner("userId").endOr();
        query.orderBy(TaskQueryProperty.CREATE_TIME).desc();
        final List<Task> list1 = query.list();
        final long count = query.count();
        final List<Task> tasks = query.listPage(1, 10);
        return null;
    }

}