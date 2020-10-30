package com.project.csr.model.vo;


import com.project.csr.model.po.ScoreChannelPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分渠道成绩统计表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ScoreChannelVo extends ScoreChannelPo implements Serializable {

}
