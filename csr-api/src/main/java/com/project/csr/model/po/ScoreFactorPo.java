package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分因子成绩统计表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("score_factor")
@ApiModel(value = "ScoreFactorPo对象", description = "分因子成绩统计表")
public class ScoreFactorPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "范围id")
    private Long scopeId;

    @ApiModelProperty(value = "店code")
    private String storeCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "因子code")
    private String factorCode;

    @ApiModelProperty(value = "分因子分数")
    private String score;

    @ApiModelProperty(value = "排名")
    private Integer rankCountry;

    @ApiModelProperty(value = "区域排名")
    private Integer rankScope;


}
