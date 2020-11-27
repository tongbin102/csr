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
public class RegulationScoreImportVo {

    /**
     * 期数
     */
    private String period;

    /**
     * 经销店名
     */
    private String storeName;

    /**
     * 单位代码
     */
    private String storeCode;

    /**
     * 因子
     */
    private String factorName;

    /**
     * 要素
     */
    private String elementName;

    /**
     * 细则
     */
    private String regulationDescription;

    /**
     * 渠道
     */
    private String channelName;

    /**
     * 类别
     */
    private String scoreType;

    /**
     * 分数
     */
    private String score;

    /**
     * 等级
     */
    private String grade;

}
