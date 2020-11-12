package com.project.csr.model.vo;


import com.project.csr.model.po.QuestionSurveyPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 客户调研-题目明细表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionSurveyVo extends QuestionSurveyPo implements Serializable {

    private String scoreType;

}
