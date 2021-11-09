package com.itblare.workflow.support.model.req;

/**
 * 抄送信息参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/8 18:58
 */
public class CcDto {

    /**
     * 抄送人ID
     */
    private String userId;

    /**
     * 抄送人用户ID
     */
    private String userName;

    /**
     * 抄送人别名
     */
    private String nikeName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return String.format("%s（%s：%s）", this.nikeName, this.userName, this.userId);
    }
}
