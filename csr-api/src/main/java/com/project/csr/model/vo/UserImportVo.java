package com.project.csr.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bin.tong
 * @date: 2020/11/24 9:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImportVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 权限码
     */
    private String ruleCode;
}
