package com.project.csr.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bin.tong
 * @date: 2020/11/25 13:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegulationImportVo {

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
    private String description;

    /**
     * 类型
     */
    private String scoreType;

    /**
     * 对应题目
     */
    private String questionSeriesNo;

    private Long factorId;

    private Long elementId;

    private Long regulationId;
}
