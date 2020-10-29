package com.project.csr.model.vo;


import com.project.csr.model.po.TestPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *  信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-29
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TestVo extends TestPo implements Serializable {

}
