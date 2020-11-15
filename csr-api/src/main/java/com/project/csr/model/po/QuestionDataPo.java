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
 * 道路救援评分规则表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("question_data")
@ApiModel(value="QuestionDataPo对象", description="道路救援评分规则表")
public class QuestionDataPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "细则id")
    private Long regulationId;

    @ApiModelProperty(value = "题目序号")
    private String seriesNo;

    @ApiModelProperty(value = "分析要点")
    private String analysisPoint;

    @ApiModelProperty(value = "KPI指标")
    private String kpi;

    @ApiModelProperty(value = "KPI说明")
    private String kpiDescription;

    @ApiModelProperty(value = "不达标后扣分")
    private String deduct;


}
