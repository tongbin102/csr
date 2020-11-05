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
 * TSS-1 因子要素映射配置表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("factor_channel_question")
@ApiModel(value="FactorChannelQuestionPo对象", description="TSS-1 因子要素映射配置表")
public class FactorChannelQuestionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    private Long factorId;

    private Integer channelId;

    @ApiModelProperty(value = "对应题目")
    private String questions;


}
