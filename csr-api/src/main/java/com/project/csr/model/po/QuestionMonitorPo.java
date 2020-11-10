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
 * 过程监控-题目明细表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("question_monitor")
@ApiModel(value="QuestionMonitorPo对象", description="过程监控-题目明细表")
public class QuestionMonitorPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "细则id")
    private Long specificId;

    @ApiModelProperty(value = "题目序号")
    private String seriesNo;

    @ApiModelProperty(value = "考核方法建议")
    private String suggestion;

    @ApiModelProperty(value = "相关说明")
    private String desc;

    @ApiModelProperty(value = "优秀")
    private String excellent;

    @ApiModelProperty(value = "优良")
    private String good;

    @ApiModelProperty(value = "达标")
    private String standard;

    @ApiModelProperty(value = "薄弱")
    private String weak;


}
