package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 成绩排名表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("score")
@ApiModel(value = "ScorePo对象", description = "成绩排名表")
public class ScorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "范围id")
    private Long scopeId;

    @ApiModelProperty(value = "店code")
    private String storeCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "总分数")
    private String score;

    @ApiModelProperty(value = "排名")
    private Integer rankCountry;

    @ApiModelProperty(value = "区域排名")
    private Integer rankScope;

}
