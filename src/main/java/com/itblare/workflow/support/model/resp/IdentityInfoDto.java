package com.itblare.workflow.support.model.resp;

/**
 * 身份信息DTO
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 16:12
 */
public class IdentityInfoDto {

    /**
     * 身份信息ID
     */
    private String identityId;

    /**
     * 身份信息类型
     */
    private String identityType;

    /**
     * 身份信息名称
     */
    private String identityName;

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }
}
