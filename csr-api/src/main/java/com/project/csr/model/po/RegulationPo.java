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
 * 细则表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("regulation")
@ApiModel(value = "RegulationPo对象", description = "细则表")
public class RegulationPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "因子要素id")
    private Long elementId;

    @ApiModelProperty(value = "细则描述")
    private String description;

    @ApiModelProperty(value = "类别")
    private String scoreType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegulationPo that = (RegulationPo) o;
        return Objects.equals(elementId, that.elementId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, description);
    }

}
