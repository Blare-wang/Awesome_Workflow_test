package com.itblare.workflow.service;

import com.itblare.workflow.support.model.req.IdentityDto;
import com.itblare.workflow.support.model.req.ProcessDefinitionDto;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

/**
 * 流程定义服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/13 14:25
 */
public interface ProcessDefinitionService {

    /**
     * 部署流程定义
     *
     * @param key         定义KEY
     * @param name        定义名称
     * @param category    分类
     * @param description 描述
     * @param tenantId    业务系统标识ID
     * @param file        部署文件
     * @author Blare
     */
    void addDeploy(String key, String name, String category, String description, String tenantId, MultipartFile file);

    /**
     * 删除流程定义
     *
     * @param processDefinitionId 流程定义ID
     * @param cascade             级联删除
     * @author Blare
     */
    void delete(String processDefinitionId, Boolean cascade);

    /**
     * 激活流程定义
     *
     * @param processDefinitionDto 流程定义ID
     * @author Blare
     */
    void activate(ProcessDefinitionDto processDefinitionDto);

    /**
     * 挂起流程定义
     *
     * @param processDefinitionDto 流程定义信息
     * @author Blare
     */
    void suspend(ProcessDefinitionDto processDefinitionDto);

    /**
     * 保存流程授权
     *
     * @param identityDto 授权信息
     * @author Blare
     */
    void addIdentityLink(IdentityDto identityDto);

    /**
     * 删除流程授权
     *
     * @param identityDto 授权信息
     * @author Blare
     */
    void deleteIdentityLink(IdentityDto identityDto);

    /**
     * 查询单一流程定义
     *
     * @param processDefinitionId 流程定义ID
     * @return {@link ProcessDefinition}
     * @author Blare
     */
    ProcessDefinition getById(String processDefinitionId);
}