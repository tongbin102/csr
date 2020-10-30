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
 * 
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("store")
@ApiModel(value="StorePo对象", description="")
public class StorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位代码")
    private String code;

    @ApiModelProperty(value = "经销店名")
    private String name;

    @ApiModelProperty(value = "所属一级")
    private String superior;

    @ApiModelProperty(value = "规模")
    private String scale;

    @ApiModelProperty(value = "全国大区")
    private String region;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;


}
