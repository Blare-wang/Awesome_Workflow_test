package com.itblare.workflow.support.model.resp;

/**
 * 附件信息
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 11:59
 */
public class ProcAttachmentDto {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件描述
     */
    private String attachmentDesc;

    /**
     * 附件链接
     */
    private String url;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentDesc() {
        return attachmentDesc;
    }

    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
