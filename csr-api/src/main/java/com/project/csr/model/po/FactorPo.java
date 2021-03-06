package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * <p>
 * 因子表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("factor")
@ApiModel(value = "FactorPo对象", description = "因子表")
public class FactorPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "因子code")
    private String code;

    @ApiModelProperty(value = "因子名称")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FactorPo that = (FactorPo) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
