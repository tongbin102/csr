package com.project.csr.model.vo;


import com.project.csr.model.po.QuestionComplainPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 投诉单表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionComplainVo extends QuestionComplainPo implements Serializable {

}
