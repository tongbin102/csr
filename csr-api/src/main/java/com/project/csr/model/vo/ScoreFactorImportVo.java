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
public class ScoreFactorImportVo {

    /**
     * 期数
     */
    private String period;

    /**
     * 层级
     */
    private String scopeName;

    /**
     * 经销店名
     */
    private String storeName;

    /**
     * 单位代码
     */
    private String storeCode;

    /**
     * 类别
     */
    private String factorName;

    /**
     * 成绩
     */
    private String score;

    private Long scopeId;

    private String regionCode;

}
