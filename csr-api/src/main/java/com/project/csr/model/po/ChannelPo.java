package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("channel")
@ApiModel(value="ChannelPo对象", description="渠道表")
public class ChannelPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "渠道code")
    private String code;

    @ApiModelProperty(value = "渠道名称")
    private String name;

    @ApiModelProperty(value = "渠道类型（1考核；2扣分）")
    private Integer ctype;


}
