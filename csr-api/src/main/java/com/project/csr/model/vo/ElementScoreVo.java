package com.project.csr.model.vo;


import com.project.csr.model.po.ElementScorePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分项成绩统计表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ElementScoreVo extends ElementScorePo implements Serializable {

}
