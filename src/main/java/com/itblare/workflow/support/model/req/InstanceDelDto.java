package com.itblare.workflow.support.model.req;

/**
 * 流程实例删除参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/24 14:56
 */
public class InstanceDelDto extends InstanceCommonDto {

    /**
     * 是否级联删除
     */
    private boolean cascade;

    /**
     * 删除原由
     */
    private String delReason;

    public boolean isCascade() {
        return cascade;
    }

    public void setCascade(boolean cascade) {
        this.cascade = cascade;
    }

    public String getDelReason() {
        return delReason;
    }

    public void setDelReason(String delReason) {
        this.delReason = delReason;
    }
}
