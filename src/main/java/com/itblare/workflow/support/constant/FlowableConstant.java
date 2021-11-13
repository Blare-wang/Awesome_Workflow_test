package com.itblare.workflow.support.constant;

/**
 * 工作流常量
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/8 16:00
 */
public class FlowableConstant {

    /**
     * 约定的发起者节点id-taskDefinitionKey
     */
    public final static String INITIATOR = "_initiator_";

    public final static String SPECIAL_GATEWAY_BEGIN_SUFFIX = "_begin";

    public final static String SPECIAL_GATEWAY_END_SUFFIX = "_end";

    /**
     * 身份类型-用户
     */
    public final static int IDENTITY_USER = 1;

    /**
     * 身份类型-组
     */
    public final static int IDENTITY_GROUP = 2;

    /**
     * 抄送
     */
    public static final String CC = "CC";

    /**
     * 任务执行批注类型
     */
    public static final String TASK_EXEC_ACTION = "AddExecComment";

    /**
     * 任务审批批注类型
     */
    public static final String TASK_AUDIT_ACTION = "AddAuditComment";

    /**
     * 表单数据
     */
    public final static String FORM_DATA = "formData";

    /**
     * 代办标识
     */
    public final static String CATEGORY_TODO = "todo";

    /**
     * 可读类别
     */
    public final static String CATEGORY_TO_READ = "toRead";

    /**
     * 按钮标记
     */
    public final static String BUTTONS = "buttons";

    /**
     * flowable bpmn命名空间名称
     */
    public final static String FLOWABLE_NAMESPACE = "http://flowable.org/bpmn";

    /**
     * 部署bpmn文件类型
     */
    public final static String FILE_EXTENSION_BPMN = ".bpmn";

    /**
     * 部署bpmn20文件类型
     */
    public final static String FILE_EXTENSION_BPMN20 = ".bpmn20.xml";

    /**
     * 部署BAR压缩包类型
     */
    public final static String FILE_EXTENSION_BAR = ".bar";

    /**
     * 部署ZIP压缩包类型
     */
    public final static String FILE_EXTENSION_ZIP = ".zip";

    public final static String PROCESS_INSTANCE_FORM_DATA = "processInstanceFormData";
}