package com.itblare.workflow.support.enumerate;

/**
 * 批注类型
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 14:44
 */
public enum CommentType {

    TJ("提交"),
    CXTJ("重新提交"),
    QS("签收"),
    QXQS("取消签收"),
    SP("审批"),
    WC("完成"),
    TH("退回"),
    CH("撤回"),
    ZC("暂存"),
    ZB("转办"),
    WP("委派"),
    ZZ("终止"),
    CC("抄送"),
    YY("已阅");

    CommentType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEnumMsgByType(String type) {
        for (CommentType e : CommentType.values()) {
            if (e.toString().equals(type)) {
                return e.name;
            }
        }
        return "";
    }
}