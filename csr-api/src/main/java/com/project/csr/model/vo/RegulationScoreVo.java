package com.project.csr.model.vo;


import com.project.csr.model.po.RegulationScorePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 细则-分数关系表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RegulationScoreVo extends RegulationScorePo implements Serializable {

    private Long elementId;

    private String elementName;

    private String regulationDescription;

}
