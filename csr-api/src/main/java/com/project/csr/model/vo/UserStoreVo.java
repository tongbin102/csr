package com.project.csr.model.vo;


import com.project.csr.model.po.UserStorePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户-区域/店关系表 信息
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserStoreVo extends UserStorePo implements Serializable {

}