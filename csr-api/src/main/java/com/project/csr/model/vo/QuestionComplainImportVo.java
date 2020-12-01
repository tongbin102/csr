package com.project.csr.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bin.tong
 * @date: 2020/11/24 11:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionComplainImportVo {

    /**
     * 月份
     */
    private String period;

    /**
     * 投诉单号
     */
    private String seriesNo;

    /**
     * 经销点名
     */
    private String storeName;

    /**
     * 单位代码
     */
    private String storeCode;

    /**
     * 渠道code
     */
    private String factorCode;

    /**
     * 描述
     */
    private String description;

}
