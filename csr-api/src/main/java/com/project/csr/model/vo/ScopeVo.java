package com.project.csr.model.vo;


import com.project.csr.model.po.ScopePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 范围表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ScopeVo extends ScopePo implements Serializable {

}
