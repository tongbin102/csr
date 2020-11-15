package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店题目-得分表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("score_question")
@ApiModel(value = "ScoreQuestionPo对象", description = "门店题目-得分表")
public class ScoreQuestionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "store_id")
    private Long storeId;

    @ApiModelProperty(value = "渠道id")
    private Long channelId;

    @ApiModelProperty(value = "题目id")
    private Long questionId;

    @ApiModelProperty(value = "得分")
    private String score;

    @ApiModelProperty(value = "等级")
    private String grade;


}
