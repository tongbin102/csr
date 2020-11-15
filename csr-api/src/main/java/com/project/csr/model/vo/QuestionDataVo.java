package com.project.csr.model.vo;


import com.project.csr.model.po.QuestionDataPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 道路救援评分规则表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionDataVo extends QuestionDataPo implements Serializable {

}
