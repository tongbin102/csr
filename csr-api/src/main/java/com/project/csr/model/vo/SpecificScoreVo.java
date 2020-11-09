package com.project.csr.model.vo;


import com.project.csr.model.po.SpecificScorePo;
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
 * @since 2020-11-09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SpecificScoreVo extends SpecificScorePo implements Serializable {

    private Long elementId;

    private String elementName;

    private String specificName;

    private Map<String, Object> specificScoreChannelMap;
}
