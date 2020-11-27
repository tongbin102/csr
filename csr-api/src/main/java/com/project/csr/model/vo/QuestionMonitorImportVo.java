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
public class QuestionMonitorImportVo {

    /**
     * 题目序号
     */
    private String seriesNo;

    /**
     * 考核方法建议
     */
    private String suggestion;

    /**
     * 题目描述
     */
    private String description;

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
