package com.project.csr.model.vo;


import com.project.csr.model.po.ScoreQuestionPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 门店题目-得分表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ScoreQuestionVo extends ScoreQuestionPo implements Serializable {

}
