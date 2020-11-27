package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 因子要素表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-05
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("element")
@ApiModel(value = "ElementPo对象", description = "因子要素表")
public class ElementPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "要素code")
    private String code;

    @ApiModelProperty(value = "因子")
    private Long factorId;

    @ApiModelProperty(value = "考核单元")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ElementPo that = (ElementPo) o;
        return Objects.equals(factorId, that.factorId) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factorId, code);
    }
}
