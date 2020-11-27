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
 * 省份表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("province")
@ApiModel(value = "ProvincePo对象", description = "省份表")
public class ProvincePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "省份code")
    private String code;

    @ApiModelProperty(value = "省份名称")
    private String name;

    @ApiModelProperty(value = "所属大区code")
    private String regionCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProvincePo that = (ProvincePo) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
