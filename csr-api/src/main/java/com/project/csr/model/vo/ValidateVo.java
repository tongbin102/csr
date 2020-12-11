package com.project.csr.model.vo;


import com.project.csr.model.po.ValidatePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 验证表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ValidateVo extends ValidatePo implements Serializable {

}
