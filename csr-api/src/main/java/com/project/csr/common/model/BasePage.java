package com.project.csr.common.model;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author: bin.tong
 * @date: 2020/10/29 16:06
 **/
public abstract class BasePage {

    /**
     * 每页要显示的记录数
     */
    private static final Integer PAGE_SIZE = 10;

    /**
     * 当前页号
     */
    private static final Integer PAGE_NO = 1;

    /**
     * 总记录数
     */
    private static final Integer RECORD_COUNT = 0;


    /**
     * 每页要显示的记录数
     */
    @TableField(exist = false)
    private Integer pageSize = PAGE_SIZE;

    /**
     * 当前页号
     */
    @TableField(exist = false)
    private Integer pageNo = PAGE_NO;

    /**
     * 总记录数
     */
    @TableField(exist = false)
    private Integer recordCount = RECORD_COUNT;

    /**
     * 每页要显示的记录数
     *
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 每页要显示的记录数
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? PAGE_SIZE : pageSize;
    }

    /**
     * 当前页号(从1开始的，所以要-1)
     *
     * @return
     */
    public Integer getPageNo() {
        if (pageNo == null) {
            return null;
        }
        return pageNo - 1;
    }

    /**
     * 当前页号
     *
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        if (pageNo != null) {
            this.pageNo = pageNo;
        }
    }

    /**
     * 总记录数
     *
     * @return
     */
    public Integer getRecordCount() {
        return recordCount;
    }

    /**
     * 总记录数
     *
     * @param recordCount
     */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    @Override
    public String toString() {
        return "BasePage{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", recordCount=" + recordCount +
                '}';
    }
}
