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
public class QuestionRescueImportVo {

    /**
     * 题目序号
     */
    private String seriesNo;

    /**
     * 分析要点
     */
    private String analysisPoint;

    /**
     * KPI指标
     */
    private String kpi;

    /**
     * KPI说明
     */
    private String kpiDescription;

    /**
     * 不达标后扣分
     */
    private String deduct;

}
