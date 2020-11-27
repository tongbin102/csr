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
 * 大区表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("region")
@ApiModel(value = "RegionPo对象", description = "大区表")
public class RegionPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "大区code")
    private String code;

    @ApiModelProperty(value = "大区名称")
    private String name;

    @ApiModelProperty(value = "所属国家code")
    private String nationalCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegionPo that = (RegionPo) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
