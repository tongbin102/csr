package com.project.csr.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: bin.tong
 * @date: 2020/11/24 11:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreImportVo {
    /**
     * 代理商代码
     */
    private String code;

    private String name;

    private String scale;

    private String superiorCode;

    private String superiorName;

    private String superiorCity;

    private String city;

    private String province;

    private String areaManager;

    private String region;

    private String regionManager;
}
