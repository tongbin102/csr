
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ProvincePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ProvinceVo;

/**
 * <p>
 * 省份表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
public interface ProvinceService extends IService<ProvincePo> {

    /**
     * 分页查询
     *
     * @param provinceVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ProvincePo>
     * @author bin.tong
     * @since 2020-11-13
     */
    IPage<ProvincePo> findListByPage(ProvinceVo provinceVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-13
     */
    boolean prohibitById(String id);
}