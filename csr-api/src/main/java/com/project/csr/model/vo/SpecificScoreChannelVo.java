package com.project.csr.model.vo;


import com.project.csr.model.po.SpecificScoreChannelPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 细则-分渠道得分关系表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SpecificScoreChannelVo extends SpecificScoreChannelPo implements Serializable {

    private Long elementId;

    private String elementName;

    private String specificName;

}
