package com.project.csr.model.vo;


import com.project.csr.model.po.ElementScoreChannelPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分渠道因子要素分布表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ElementScoreChannelVo extends ElementScoreChannelPo implements Serializable {

}
