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
 * 投诉单表
 * </p>
 *
 * @author bin.tong
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("question_complain")
@ApiModel(value="QuestionComplainPo对象", description="投诉单表")
public class QuestionComplainPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "投诉单号")
    private String seriesNo;

    @ApiModelProperty(value = "月份")
    private String period;

    @ApiModelProperty(value = "店code")
    private String storeCode;

    @ApiModelProperty(value = "因子code")
    private String factorCode;

    @ApiModelProperty(value = "描述")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionComplainPo that = (QuestionComplainPo) o;
        return Objects.equals(seriesNo, that.seriesNo)
                && Objects.equals(period, that.period)
                && Objects.equals(storeCode, that.storeCode)
                && Objects.equals(factorCode, that.factorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesNo, period, storeCode, factorCode);
    }

}
