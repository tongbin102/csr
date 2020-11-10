package com.project.csr.model.vo;


import com.project.csr.model.po.QuestionMonitorPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 过程监控-题目明细表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionMonitorVo extends QuestionMonitorPo implements Serializable {

}
