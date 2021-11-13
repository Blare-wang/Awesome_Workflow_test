package com.itblare.workflow.support.flowcmd;

import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.model.req.CcDto;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.persistence.entity.CommentEntity;
import org.flowable.engine.impl.persistence.entity.CommentEntityManager;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.IdentityLinkUtil;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * CMD方式处理抄送
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/11 1:19
 */
public class AddCcIdentityLinkCmd implements Command<Void>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String processInstanceId;
    protected String taskId;
    protected String userId;
    protected List<CcDto> ccDtoList;

    public AddCcIdentityLinkCmd(String processInstanceId, String taskId, String userId, List<CcDto> ccDtoList) {
        validateParams(processInstanceId, taskId, userId, ccDtoList);
        this.processInstanceId = processInstanceId;
        this.taskId = taskId;
        this.userId = userId;
        this.ccDtoList = ccDtoList;
    }

    protected void validateParams(String processInstanceId, String taskId, String userId, List<CcDto> ccDtoList) {
        if (processInstanceId == null) {
            throw new FlowableIllegalArgumentException("processInstanceId is null");
        }
        if (taskId == null) {
            throw new FlowableIllegalArgumentException("taskId is null");
        }
        if (userId == null) {
            throw new FlowableIllegalArgumentException("userId is null");
        }
        if (Objects.isNull(ccDtoList) || ccDtoList.size() == 0) {
            throw new FlowableIllegalArgumentException("ccTo is null or empty");
        }
    }

    @Override
    public Void execute(CommandContext commandContext) {
        ExecutionEntityManager executionEntityManager = CommandContextUtil.getExecutionEntityManager(commandContext);
        ExecutionEntity processInstance = executionEntityManager.findById(processInstanceId);
        if (processInstance == null) {
            throw new FlowableObjectNotFoundException("Cannot find process instance with id " + processInstanceId,
                    ExecutionEntity.class);
        }
        for (CcDto ccDto : ccDtoList) {
            IdentityLinkUtil.createProcessInstanceIdentityLink(processInstance, ccDto.getUserId(), null, FlowableConstant.CC);
        }
        this.createCcComment(commandContext);
        return null;

    }

    protected void createCcComment(CommandContext commandContext) {
        CommentEntityManager commentEntityManager = CommandContextUtil.getCommentEntityManager(commandContext);
        CommentEntity comment = (CommentEntity) commentEntityManager.create();
        comment.setProcessInstanceId(processInstanceId);
        comment.setUserId(userId);
        comment.setType(FlowableConstant.CC);
        comment.setTime(CommandContextUtil.getProcessEngineConfiguration(commandContext).getClock().getCurrentTime());
        comment.setTaskId(taskId);
        comment.setAction("AddCcTo");
        // arrayToDelimitedString(arr, ",");
        String ccToStr = StringUtils.collectionToDelimitedString(ccDtoList, ",");
        comment.setMessage(ccToStr);
        comment.setFullMessage(ccToStr);
        commentEntityManager.insert(comment);
    }
}