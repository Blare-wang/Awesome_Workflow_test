package com.itblare.workflow.support.model.req;

import java.io.Serializable;
import java.util.Set;

/**
 * 当前工作流用户，用于工作流中进行操作权限验证
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/22 10:09
 */
public class WorkFlowUserDto implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户组
     */
    private Set<String> groups;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
