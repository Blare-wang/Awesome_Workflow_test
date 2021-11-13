package com.itblare.workflow.support.flowcmd;

import com.itblare.workflow.support.constant.FlowableConstant;
import com.itblare.workflow.support.enumerate.CommentType;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.cmd.AddCommentCmd;
import org.flowable.engine.impl.cmd.NeedsActiveTaskCmd;
import org.flowable.engine.impl.util.TaskHelper;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;

/**
 * CMD方式处理任务读操作
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/11 0:57
 */
public class CompleteTaskReadCmd extends NeedsActiveTaskCmd<Void> {

    private static final long serialVersionUID = 2824921128091879469L;

    private final String userId;

    public CompleteTaskReadCmd(String taskId, String userId) {
        super(taskId);
        this.userId = userId;
    }

    @Override
    protected Void execute(CommandContext commandContext, TaskEntity task) {
        if (userId == null || userId.length() == 0) {
            throw new FlowableIllegalArgumentException("用户ID不能为空");
        }
        if (!FlowableConstant.CATEGORY_TO_READ.equals(task.getCategory())) {
            throw new FlowableException("任务分类必须是可读任务");
        }
        if (!userId.equals(task.getOwner()) && !userId.equals(task.getAssignee())) {
            throw new FlowableException("当前用户无权限");
        }
        commandContext.getCommandExecutor().execute(new AddCommentCmd(taskId, task.getProcessInstanceId(), CommentType.YY.toString(), "已阅！"));
        TaskHelper.completeTask(task, null, null, null, null, commandContext);
        return null;
    }

    @Override
    protected String getSuspendedTaskException() {
        return "Cannot complete a suspended task";
    }
}