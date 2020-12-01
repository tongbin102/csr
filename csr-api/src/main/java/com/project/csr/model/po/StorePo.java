package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-06
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("store")
@ApiModel(value = "StorePo对象", description = "门店表")
public class StorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店代码")
    private String code;

    @ApiModelProperty(value = "门店名称")
    private String name;

    @ApiModelProperty(value = "规模")
    private String scale;

    @ApiModelProperty(value = "所属大区code")
    private String regionCode;

    @ApiModelProperty(value = "所属城市code")
    private String cityCode;

    @ApiModelProperty(value = "所属一级门店code")
    private String parentCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StorePo that = (StorePo) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
