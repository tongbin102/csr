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
 * 验证表
 * </p>
 *
 * @author bin.tong
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("validate")
@ApiModel(value = "ValidatePo对象", description = "验证表")
public class ValidatePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "重置token")
    private String resetToken;

    @ApiModelProperty(value = "类型")
    private String type;


}
