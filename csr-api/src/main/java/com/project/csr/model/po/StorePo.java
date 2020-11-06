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
@ApiModel(value="StorePo对象", description="")
public class StorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位code")
    private String code;

    @ApiModelProperty(value = "经销店名")
    private String name;

    @ApiModelProperty(value = "范围id")
    private Long scopeId;

    @ApiModelProperty(value = "上级id")
    private Long parentId;


}
