package com.itblare.workflow.support.model.req;

import java.util.List;
import java.util.Map;

/**
 * 任务执行参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/22 10:46
 */
public class TaskExecDto extends TaskCommonDto {

    /**
     * 流程变量
     */
    private Map<String, Object> values;

    /**
     * 提供的变量将存储在任务本地（当任务结束后，再也取不到这个值），而不是流程实例范围（默认是存放在流程实例中）
     */
    private Map<String, Object> localValues;

    /**
     * 抄送人列表
     */
    private List<CcDto> ccTotoList;

    /**
     * 当前操作完后的流程审核状态
     */
    private String auditStatus;

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public Map<String, Object> getLocalValues() {
        return localValues;
    }

    public void setLocalValues(Map<String, Object> localValues) {
        this.localValues = localValues;
    }

    public List<CcDto> getCcTotoList() {
        return ccTotoList;
    }

    public void setCcTotoList(List<CcDto> ccTotoList) {
        this.ccTotoList = ccTotoList;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
