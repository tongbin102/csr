
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.FactorPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.FactorVo;

/**
 * <p>
 * 因子表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface FactorService extends IService<FactorPo> {

    /**
     * 分页查询
     *
     * @param factorVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.FactorPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<FactorPo> findListByPage(FactorVo factorVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-05
     */
    boolean prohibitById(String id);
}