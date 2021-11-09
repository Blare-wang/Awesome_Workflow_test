package com.itblare.workflow.service;

import com.itblare.workflow.support.model.PageInfoDto;
import com.itblare.workflow.support.model.req.ModelDto;
import com.itblare.workflow.support.model.req.ModelEditorDto;
import com.itblare.workflow.support.model.req.ModelQueryDto;
import com.itblare.workflow.support.model.resp.ModelInfoDto;
import org.flowable.engine.repository.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * 流程模型服务
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/14 9:52
 */
public interface ProcessModelService {

    // 新增流程模型
    void saveModel(ModelDto modelDto);

    // 复制流程模型
    void copyModel(String modelId);

    // 删除流程模型
    void deleteModel(Set<String> modelIds, boolean cascade);

    // 保存流程设计
    void saveModelEditor(ModelEditorDto modelEditorDto);

    // 部署流程模型
    void deployModel(String modelId);

    // 导入流程模型
    void doImport(String tenantId, MultipartFile file);

    // 获取流程模型
    Model getModelById(String id);

    // 获取非空流程模型
    Model getNonNullModelById(String id);

    // 查询流程模型详情
    ModelInfoDto getById(String id);

    // 查询流程模型列表
    PageInfoDto<ModelInfoDto> getPage(ModelQueryDto modelQueryDto);

}