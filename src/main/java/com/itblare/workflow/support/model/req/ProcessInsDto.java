package com.itblare.workflow.support.model.req;

/**
 * 流程实例参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/17 10:16
 */
public class ProcessInsDto extends WorkFlowUserDto {

    /**
     * 是否完成
     */
    private Boolean unfinished;

    /**
     * 是否逻辑删除
     */
    private Boolean deleted;

    /**
     * 分页参数
     */
    private PageDto pageDto;

    public Boolean getUnfinished() {
        return unfinished;
    }

    public void setUnfinished(Boolean unfinished) {
        this.unfinished = unfinished;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageDto pageDto) {
        this.pageDto = pageDto;
    }
}
