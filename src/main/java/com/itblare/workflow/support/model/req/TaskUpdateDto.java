package com.itblare.workflow.support.model.req;

import java.time.LocalDateTime;

/**
 * 任务修改参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/18 17:41
 */
public class TaskUpdateDto extends WorkFlowUserDto {

    /**
     * 任务ID
     */
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 被指派执行该任务的人
     */
    private String assignee;

    /**
     * 实际签收人 任务的拥有者
     */
    private String owner;

    /**
     * 过期时间
     */
    private LocalDateTime dueDate;

    /**
     * 类别标识
     */
    private String category;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 优先级
     */
    private int priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
