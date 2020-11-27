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
public class QuestionAssistanceImportVo {

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
     * 优秀
     */
    private String excellent;

    /**
     * 优良
     */
    private String good;

    /**
     * 达标
     */
    private String standard;

    /**
     * 薄弱
     */
    private String weak;

}
