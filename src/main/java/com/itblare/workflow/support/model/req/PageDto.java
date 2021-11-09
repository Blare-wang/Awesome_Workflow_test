package com.itblare.workflow.support.model.req;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/6/17 10:17
 */
public class PageDto implements Serializable {

    public PageDto(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页数据量
     */
    private int pageSize;

    /**
     * 页偏移量
     */
    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
