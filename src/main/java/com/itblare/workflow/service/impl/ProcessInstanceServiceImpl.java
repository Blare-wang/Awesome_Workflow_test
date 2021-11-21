package com.itblare.workflow.service.impl;

import com.itblare.workflow.service.PermissionService;
import com.itblare.workflow.service.ProcessInstanceService;
import com.itblare.workflow.service.ProcessTaskService;
import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.enumerate.CommentType;
import com.itblare.workflow.support.exceptions.FlowableImageException;
import com.itblare.workflow.support.flowImage.CustomProcessDiagramGenerator;
import com.itblare.workflow.support.flowcmd.AddCcIdentityLinkCmd;
import com.itblare.workflow.support.model.req.InstanceCommonDto;
import com.itblare.workflow.support.model.req.InstanceDelDto;
import com.itblare.workflow.support.model.req.InstanceStartDto;
import com.itblare.workflow.support.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceBuilder;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public byte[] generateImageByProcInstId(String processInstanceId) {
        if (CommonUtil.isEmptyAfterStrip(processInstanceId)) {
            throw new FlowableException("[异常]-传入的参数procInstId为空！");
        }
        // 通过流程实例ID获取历史流程实例
        HistoricProcessInstance historicProcessInstance = getHistoricById(processInstanceId);

        // 通过流程实例ID获取流程中已经执行的节点，按照执行先后顺序排序
        List<HistoricActivityInstance> historicActivityInstanceList = getHistoricActivityInstAsc(processInstanceId);


        // 将已经执行的节点ID放入高亮显示节点集合
        List<String> highLightedActivitiIdList = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            highLightedActivitiIdList.add(historicActivityInstance.getActivityId());
            logger.info("已执行的节点[{}-{}-{}-{}]", historicActivityInstance.getId(), historicActivityInstance
                    .getActivityId(), historicActivityInstance.getActivityName(), historicActivityInstance
                    .getAssignee());
        }

        // 通过流程实例ID获取流程中正在执行的节点
        List<Execution> runningActivityInstanceList = getRunningActivityInst(processInstanceId);
        List<String> runningActivitiIdList = new ArrayList<>(runningActivityInstanceList.size());
        for (Execution execution : runningActivityInstanceList) {
            if (StringUtils.isNotEmpty(execution.getActivityId())) {
                runningActivitiIdList.add(execution.getActivityId());
                logger.info("执行中的节点[{}-{}-{}]", execution.getId(), execution.getActivityId(), execution.getName());
            }
        }

        // 通过流程实例ID获取已经完成的历史流程实例
        List<HistoricProcessInstance> historicFinishedProcessInstanceList = getHistoricFinishedProcInst(processInstanceId);

        // 定义流程画布生成器
        CustomProcessDiagramGenerator processDiagramGenerator;
        // 如果还没完成，流程图高亮颜色为绿色，如果已经完成为红色
        // if (!CollectionUtils.isEmpty(historicFinishedProcessInstanceList)) {
        // // 如果不为空，说明已经完成
        // processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        // } else {
        processDiagramGenerator = new CustomProcessDiagramGenerator();
        //}

        // 获取流程定义Model对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());

        // 获取已流经的流程线，需要高亮显示高亮流程已发生流转的线id集合
        List<String> highLightedFlowIds = getHighLightedFlows(bpmnModel, historicActivityInstanceList);
        // 使用默认配置获得流程图表生成器，并生成追踪图片字符流
        InputStream imageStream = processDiagramGenerator.generateDiagramCustom(bpmnModel,
                "png",
                highLightedActivitiIdList,
                runningActivitiIdList,
                highLightedFlowIds,
                "宋体",
                "微软雅黑",
                "黑体",
                null,
                2.0);
        // 将InputStream数据流转换为byte[]
        try (imageStream) {
            // byte[] buffer = new byte[imageStream.available()];
            // imageStream.read(buffer);
            // return buffer;
            return imageStream.readAllBytes();
        } catch (IOException ex) {
            logger.error("通过流程实例ID[{}]获取流程图时出现异常！", ex.getMessage(), ex);
            throw new FlowableImageException("通过流程实例ID" + processInstanceId + "获取流程图时出现异常！");
        }
    }

    /**
     * 通过流程实例ID获取流程中已经执行的节点，按照执行先后顺序排序
     *
     * @param processInstanceId 流程实例ID
     */
    public List<HistoricActivityInstance> getHistoricActivityInstAsc(String processInstanceId) {
        return historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceId()
                .asc()
                .list();
    }

    /**
     * 通过流程实例ID获取流程中正在执行的节点
     *
     * @param processInstanceId 流程实例ID
     */
    public List<Execution> getRunningActivityInst(String processInstanceId) {
        return runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
    }

    /**
     * 通过流程实例ID获取已经完成的历史流程实例
     *
     * @param processInstanceId 流程实例ID
     */
    public List<HistoricProcessInstance> getHistoricFinishedProcInst(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).finished().list();
    }

    /**
     * 获取已流经的流程线，需要高亮显示高亮流程已发生流转的线id集合
     *
     * @param bpmnModel                    BPMN模型
     * @param historicActivityInstanceList 已执行的节点
     */
    public List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstanceList) {
        // 已流经的流程线，需要高亮显示
        List<String> highLightedFlowIdList = new ArrayList<>();
        // 全部活动节点
        List<FlowNode> allHistoricActivityNodeList = new ArrayList<>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstanceList = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            // 获取流程节点
            FlowElement flowElement = bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true);
            if (!flowElement.getClass().isAssignableFrom(FlowNode.class)) {
                continue;
            }
            FlowNode flowNode = (FlowNode) flowElement;
            allHistoricActivityNodeList.add(flowNode);
            // 结束时间不为空，当前节点则已经完成
            if (historicActivityInstance.getEndTime() != null) {
                finishedActivityInstanceList.add(historicActivityInstance);
            }
        }

        FlowNode currentFlowNode;
        FlowNode targetFlowNode;
        HistoricActivityInstance currentActivityInstance;
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        for (int k = 0; k < finishedActivityInstanceList.size(); k++) {
            currentActivityInstance = finishedActivityInstanceList.get(k);
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance
                    .getActivityId(), true);
            // 当前节点的所有流出线
            List<SequenceFlow> outgoingFlowList = currentFlowNode.getOutgoingFlows();

            /*
             * 遍历outgoingFlows并找到已流转的 满足如下条件认为已流转：
             * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             * (第2点有问题，有过驳回的，会只绘制驳回的流程线，通过走向下一级的流程线没有高亮显示)
             */
            if ("parallelGateway".equals(currentActivityInstance.getActivityType())
                    || "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
                // 遍历历史活动节点，找到匹配流程目标节点的
                for (SequenceFlow outgoingFlow : outgoingFlowList) {
                    // 获取当前节点流程线对应的下级节点
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(outgoingFlow.getTargetRef(),
                            true);
                    // 如果下级节点包含在所有历史节点中，则将当前节点的流出线高亮显示
                    if (allHistoricActivityNodeList.contains(targetFlowNode)) {
                        highLightedFlowIdList.add(outgoingFlow.getId());
                    }
                }
            } else {
                /*
                 * 2、当前节点不是并行网关或兼容网关
                 * 【已解决-问题】如果当前节点有驳回功能，驳回到申请节点，
                 * 则因为申请节点在历史节点中，导致当前节点驳回到申请节点的流程线被高亮显示，但实际并没有进行驳回操作
                 */
                List<Map<String, Object>> tempMapList = new ArrayList<>();
                // 当前节点ID
                String currentActivityId = currentActivityInstance.getActivityId();
                int size = historicActivityInstanceList.size();
                boolean ifStartFind = false;
                boolean ifFinded = false;
                HistoricActivityInstance historicActivityInstance;
                // 循环当前节点的所有流出线，循环所有历史节点
                logger.info("【开始】-匹配当前节点-ActivityId=【{}】需要高亮显示的流出线，\n循环历史节点", currentActivityId);
                for (int i = 0; i < historicActivityInstanceList.size(); i++) {
                    // 如果当前节点流程线对应的下级节点在历史节点中，则该条流程线进行高亮显示（【问题】有驳回流程线时，即使没有进行驳回操作，因为申请节点在历史节点中，也会将驳回流程线高亮显示-_-||）
                    /*if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("highLightedFlowId", sequenceFlow.getId());
                        map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
                        tempMapList.add(map);
                        // highLightedFlowIdList.add(sequenceFlow.getId());
                    }*/
                    // 历史节点
                    historicActivityInstance = historicActivityInstanceList.get(i);
                    logger.info("第【{}/{}】个历史节点-ActivityId=[{}]", i + 1, size, historicActivityInstance.getActivityId());
                    // 如果循环历史节点中的id等于当前节点id，从当前历史节点继续先后查找是否有当前节点流程线等于的节点
                    // 历史节点的序号需要大于等于已完成历史节点的序号，防止驳回重审一个节点经过两次是只取第一次的流出线高亮显示，第二次的不显示
                    if (i >= k && historicActivityInstance.getActivityId().equals(currentActivityId)) {
                        logger.info("第[{}]个历史节点和当前节点一致-ActivityId=[{}]", i + 1, historicActivityInstance
                                .getActivityId());
                        ifStartFind = true;
                        // 跳过当前节点继续查找下一个节点
                        continue;
                    }
                    if (ifStartFind) {
                        logger.info("[开始]-循环当前节点-ActivityId=【{}】的所有流出线", currentActivityId);
                        for (SequenceFlow sequenceFlow : outgoingFlowList) {
                            // 如果当前节点流程线对应的下级节点在其后面的历史节点中，则该条流程线进行高亮显示
                            // 【问题】
                            logger.info("当前流出线的下级节点=[{}]", sequenceFlow.getTargetRef());
                            if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                                logger.info("当前节点[{}]需高亮显示的流出线=[{}]", currentActivityId, sequenceFlow.getId());
                                highLightedFlowIdList.add(sequenceFlow.getId());
                                // 暂时默认找到离当前节点最近的下一级节点即退出循环，否则有多条流出线时将全部被高亮显示
                                ifFinded = true;
                                break;
                            }
                        }
                        logger.info("[完成]-循环当前节点-ActivityId=【{}】的所有流出线", currentActivityId);
                    }
                    if (ifFinded) {
                        // 暂时默认找到离当前节点最近的下一级节点即退出历史节点循环，否则有多条流出线时将全部被高亮显示
                        break;
                    }
                }
                logger.info("【完成】-匹配当前节点-ActivityId=【{}】需要高亮显示的流出线", currentActivityId);
                /*if (Objects.nonNull(tempMapList) && tempMapList.size() > 0) {
                    // 遍历匹配的集合，取得开始时间最早的一个
                    long earliestStamp = 0L;
                    String highLightedFlowId = null;
                    for (Map<String, Object> map : tempMapList) {
                        long highLightedFlowStartTime = Long.valueOf(map.get("highLightedFlowStartTime").toString());
                        if (earliestStamp == 0 || earliestStamp <= highLightedFlowStartTime) {
                            highLightedFlowId = map.get("highLightedFlowId").toString();
                            earliestStamp = highLightedFlowStartTime;
                        }
                    }
                    highLightedFlowIdList.add(highLightedFlowId);
                }*/
            }

        }
        return highLightedFlowIdList;
    }

}