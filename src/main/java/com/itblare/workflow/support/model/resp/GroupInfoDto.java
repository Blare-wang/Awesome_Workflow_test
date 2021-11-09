package com.itblare.workflow.support.model.resp;

/**
 * 用户组信息DTO
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 10:58
 */
public class GroupInfoDto {

    /**
     * 组ID
     */
    private String id;

    /**
     * 组名称
     */
    private String name;

    /**
     * 组标识Key
     */
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
