package com.project.csr.model.vo;


import com.project.csr.model.po.ChannelPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 渠道表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ChannelVo extends ChannelPo implements Serializable {

}
