package com.itblare.workflow.support.listener;

import com.itblare.workflow.support.constant.FlowableConstant;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEntityEventImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 任务处理前置监听器
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/14 0:45
 */
@Component
public class TaskBeforeListener implements FlowableEventListener {

    @Override
    public void onEvent(FlowableEvent flowableEvent) {
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) flowableEvent).getEntity();
        if (Objects.isNull(taskEntity.getCategory())) {
            taskEntity.setCategory(FlowableConstant.CATEGORY_TODO);
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}