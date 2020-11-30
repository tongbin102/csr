package com.project.csr.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.csr.common.model.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 用户-区域/店关系表
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-24
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("user_store")
@ApiModel(value = "UserStorePo对象", description = "用户-区域/店关系表")
public class UserStorePo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户code")
    private String userCode;

    @ApiModelProperty(value = "范围id")
    private Long scopeId;

    @ApiModelProperty(value = "所辖省份code")
    private String provinceCode;

    @ApiModelProperty(value = "所辖城市店code")
    private String cityCode;

    @ApiModelProperty(value = "所辖一级店code")
    private String superiorCode;

    @ApiModelProperty(value = "所辖二级店code")
    private String storeCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStorePo that = (UserStorePo) o;
        return Objects.equals(userCode, that.userCode) &&
                Objects.equals(scopeId, that.scopeId) &&
                Objects.equals(storeCode, that.storeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCode, scopeId, storeCode);
    }
}
