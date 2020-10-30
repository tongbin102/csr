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
 * 分渠道因子要素分布表
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("element_score_channel")
@ApiModel(value="ElementScoreChannelPo对象", description="分渠道因子要素分布表")
public class ElementScoreChannelPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String periods;

    @ApiModelProperty(value = "范围id")
    private Integer scopeId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "因子要素id")
    private String elementId;

    @ApiModelProperty(value = "分因子分数")
    private String score;

    @ApiModelProperty(value = "成绩类型：1.考核项目 2. 加分项")
    private Boolean scoreType;


}
