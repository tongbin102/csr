package com.project.csr.model.vo;


import com.project.csr.model.po.CityPo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 城市表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CityVo extends CityPo implements Serializable {

}
