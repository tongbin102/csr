package com.project.csr.model.vo;


import com.project.csr.model.po.ElementPo;
import com.project.csr.model.po.RegulationPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 细则表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RegulationVo extends RegulationPo implements Serializable {

    private ElementPo elementPo;

}
