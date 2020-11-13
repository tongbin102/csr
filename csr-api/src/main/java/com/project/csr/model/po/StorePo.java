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
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("store")
@ApiModel(value="StorePo对象", description="门店表")
public class StorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店代码")
    private String code;

    @ApiModelProperty(value = "门店名称")
    private String name;

    @ApiModelProperty(value = "规模")
    private String scale;

    @ApiModelProperty(value = "所属城市id")
    private Long cityId;

    @ApiModelProperty(value = "所属一级门店id")
    private Long parentId;

}
