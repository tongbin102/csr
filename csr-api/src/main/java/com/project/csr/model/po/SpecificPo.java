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
 * 细则
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("specific")
@ApiModel(value="SpecificPo对象", description="细则")
public class SpecificPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "因子要素id")
    private Long elementId;

    @ApiModelProperty(value = "细则描述")
    private String desc;

    @ApiModelProperty(value = "类别")
    private String score_type;


}
