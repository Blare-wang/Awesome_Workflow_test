package com.itblare.workflow.support.model.resp;

/**
 * 流程定义DTO
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/25 16:33
 */
public class ProcessDefDto {

    /**
     * 流程定义ID
     */
    private String id;

    /**
     * 流程定义KEY
     */
    private String key;

    /**
     * 流程定义版本
     */
    private int version;

    /**
     * 流程定义名称
     */
    private String name;

    /**
     * 流程定义描述说明
     */
    private String description;

    /**
     * 业务系统标识ID
     */
    private String tenantId;

    /**
     * 流程定义分类
     */
    private String category;

    /**
     * 流程定义表单key
     */
    private String formKey;

    /**
     * 是否图形符号定义
     */
    private boolean graphicalNotationDefined = false;

    /**
     * 是否挂起
     */
    private boolean suspended = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public boolean isGraphicalNotationDefined() {
        return graphicalNotationDefined;
    }

    public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
        this.graphicalNotationDefined = graphicalNotationDefined;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
