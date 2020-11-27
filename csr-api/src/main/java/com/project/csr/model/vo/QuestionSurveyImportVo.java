package com.project.csr.model.vo;

import io.swagger.annotations.ApiModelProperty;
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
public class QuestionSurveyImportVo {

    /**
     * 题目序号
     */
    private String seriesNo;

    /**
     * 题目描述
     */
    private String description;

    /**
     * 答案1
     */
    private String answer1;

    /**
     * 答案2
     */
    private String answer2;

    /**
     * 答案3
     */
    private String answer3;

    /**
     * 答案4
     */
    private String answer4;

    /**
     * 答案5
     */
    private String answer5;

    /**
     * 计分答案项
     */
    private String scoreItem;

    /**
     * 公式号
     */
    private String formula;

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
