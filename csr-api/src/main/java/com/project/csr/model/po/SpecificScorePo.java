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
 * 细则-分数关系表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("specific_score")
@ApiModel(value="SpecificScorePo对象", description="细则-分数关系表")
public class SpecificScorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "storeId")
    private Long storeId;

    @ApiModelProperty(value = "细则id")
    private Long specificId;

    @ApiModelProperty(value = "考核项")
    private String evaluateScore;

    @ApiModelProperty(value = "加分项")
    private String bonusScore;


}
