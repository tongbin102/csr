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
 * 大区表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("region")
@ApiModel(value="RegionPo对象", description="大区表")
public class RegionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "大区code")
    private String code;

    @ApiModelProperty(value = "大区名称")
    private String name;


}
