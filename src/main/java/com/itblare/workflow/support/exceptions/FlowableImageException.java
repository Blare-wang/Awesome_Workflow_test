package com.itblare.workflow.support.exceptions;

/**
 * 流程图生成异常
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/21 23:13
 */
public class FlowableImageException extends BaseException {

    private static final long serialVersionUID = 2186678685285116641L;

    public FlowableImageException(String messag) {
        super(400, messag);
    }

    public FlowableImageException(int code, String message) {
        super(code, message);
    }

    public FlowableImageException(int code, String message, Throwable ex) {
        super(code, message, ex);
    }

    public FlowableImageException(Throwable exception) {
        super(exception);
    }
}