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
 * 
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test")
@ApiModel(value="TestPo对象", description="")
public class TestPo extends BasePo {

    private static final long serialVersionUID = 1L;

    private String name;


}
