package com.itblare.workflow.support.exceptions;

/**
 * Flowable无权限异常
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/11/14 15:58
 */
public class FlowableNoPermissionException  extends BaseException{

    private static final long serialVersionUID = -2960659802639629532L;

    public FlowableNoPermissionException(String messag){
        super(400, messag);
    }

    public FlowableNoPermissionException(int code, String message) {
        super(code, message);
    }

    public FlowableNoPermissionException(int code, String message, Throwable ex) {
        super(code, message, ex);
    }

    public FlowableNoPermissionException(Throwable exception) {
        super(exception);
    }
}