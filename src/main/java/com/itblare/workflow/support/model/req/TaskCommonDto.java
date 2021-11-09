package com.itblare.workflow.support.model.req;

import java.util.List;

/**
 * 任务公共参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/24 10:48
 */
public class TaskCommonDto extends WorkFlowUserDto {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 批注信息
     */
    private CommentDto commentDto;

    /**
     * 附件信息
     */
    private List<AttachmentDto> attachmentDtoList;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public CommentDto getCommentDto() {
        return commentDto;
    }

    public void setCommentDto(CommentDto commentDto) {
        this.commentDto = commentDto;
    }

    public List<AttachmentDto> getAttachmentDtoList() {
        return attachmentDtoList;
    }

    public void setAttachmentDtoList(List<AttachmentDto> attachmentDtoList) {
        this.attachmentDtoList = attachmentDtoList;
    }
}
