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
 * TSS-3 过程监控要求
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("question_monitor")
@ApiModel(value="QuestionMonitorPo对象", description="TSS-3 过程监控要求")
public class QuestionMonitorPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属渠道")
    private Long channelId;

    @ApiModelProperty(value = "问题code")
    private String code;

    @ApiModelProperty(value = "调研问题")
    private String desc;

    @ApiModelProperty(value = "相关说明")
    private String illustration;

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
