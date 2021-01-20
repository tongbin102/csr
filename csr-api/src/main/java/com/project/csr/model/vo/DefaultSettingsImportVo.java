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
public class DefaultSettingsImportVo {

    /**
     * 渠道
     */
    private String channelName;

    /**
     * 题目为空
     */
    private String isEmptyQuestion;

    /**
     * 样本为空
     */
    private String isEmptySample;

    /**
     * 是否纳入评价
     */
    private String evaluateType;




}
