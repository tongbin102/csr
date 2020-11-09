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
 * 细则-分渠道得分关系表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("specific_score_channel")
@ApiModel(value="SpecificScoreChannelPo对象", description="细则-分渠道得分关系表")
public class SpecificScoreChannelPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "storeId")
    private Long storeId;

    @ApiModelProperty(value = "细则id")
    private Long specificId;

    @ApiModelProperty(value = "渠道id")
    private Long channelId;

    @ApiModelProperty(value = "成绩类型：1.考核项目 2. 加分项")
    private Boolean scoreType;

    @ApiModelProperty(value = "分数")
    private String score;

    @ApiModelProperty(value = "等级")
    private String grade;


}
