package com.itblare.workflow.support.enumerate;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * 钮权类型
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 15:25
 */
public enum ButtonType {

    COMPLETE("提交"),
    STOP("终止"),
    ASSIGN("转办"),
    DELEGATE("委派"),
    BACK("退回"),
    DELETE("删除"),
    TAKE_BACK("撤回");

    ButtonType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static HashMap<String, ButtonType> lookup = new HashMap<>();

    static {
        for (ButtonType typeEnum : EnumSet.allOf(ButtonType.class)) {
            lookup.put(typeEnum.toString(), typeEnum);
        }
    }

    public static ButtonType get(String value) {
        return lookup.get(value);
    }
}