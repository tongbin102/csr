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
 * 城市表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("city")
@ApiModel(value="CityPo对象", description="城市表")
public class CityPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "城市code")
    private String code;

    @ApiModelProperty(value = "城市名称")
    private String name;

    @ApiModelProperty(value = "所属省份id")
    private Long provinceId;


}
