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
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("regulation_score_channel")
@ApiModel(value="RegulationScoreChannelPo对象", description="细则-分渠道得分关系表")
public class RegulationScoreChannelPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "店code")
    private String storeCode;

    @ApiModelProperty(value = "细则描述")
    private String regulationDescription;

    @ApiModelProperty(value = "渠道code")
    private String channelCode;

    @ApiModelProperty(value = "成绩类型：1.考核项 2. 加分项 3. 扣分项")
    private Integer scoreType;

    @ApiModelProperty(value = "得分")
    private String score;

    @ApiModelProperty(value = "等级")
    private String grade;

}
