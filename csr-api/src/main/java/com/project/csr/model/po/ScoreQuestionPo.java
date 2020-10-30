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
 * 分项成绩统计表
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("score_question")
@ApiModel(value="ScoreQuestionPo对象", description="分项成绩统计表")
public class ScoreQuestionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期数")
    private String periods;

    @ApiModelProperty(value = "范围id")
    private Integer scopeId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "问题code")
    private String question;

    @ApiModelProperty(value = "分因子分数")
    private String score;


}
