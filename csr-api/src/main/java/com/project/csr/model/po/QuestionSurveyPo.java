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
 * TSS-2 调研问卷评分规则配置表
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("question_survey")
@ApiModel(value="QuestionSurveyPo对象", description="TSS-2 调研问卷评分规则配置表")
public class QuestionSurveyPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属渠道")
    private Long channelId;

    @ApiModelProperty(value = "问题code")
    private String code;

    @ApiModelProperty(value = "调研问题")
    private String desc;

    @ApiModelProperty(value = "答案1")
    private String answer1;

    @ApiModelProperty(value = "答案2")
    private String answer2;

    @ApiModelProperty(value = "答案3")
    private String answer3;

    @ApiModelProperty(value = "答案4")
    private String answer4;

    @ApiModelProperty(value = "答案5")
    private String answer5;

    @ApiModelProperty(value = "计分答案项")
    private String scoreItem;

    @ApiModelProperty(value = "优秀")
    private String excellent;

    @ApiModelProperty(value = "优良")
    private String good;

    @ApiModelProperty(value = "达标")
    private String standard;

    @ApiModelProperty(value = "薄弱")
    private String weak;

    @ApiModelProperty(value = "类别")
    private String scoreType;


}
