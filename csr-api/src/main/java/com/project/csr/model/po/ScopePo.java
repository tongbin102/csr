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
 * 范围表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("scope")
@ApiModel(value="ScopePo对象", description="范围表")
public class ScopePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "范围code")
    private String code;

    @ApiModelProperty(value = "范围名称")
    private String name;


}
