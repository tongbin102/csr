
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RegulationPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.RegulationVo;

import java.util.List;

/**
 * <p>
 * 因子要素细则表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
public interface RegulationService extends IService<RegulationPo> {

    /**
     * 分页查询
     *
     * @param regulationVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RegulationPo>
     * @author bin.tong
     * @since 2020-11-11
     */
    IPage<RegulationPo> findListByPage(RegulationVo regulationVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-11
     */
    boolean prohibitById(String id);

    /**
     * 通过ids查询列表
     *
     * @param ids
     * @return
     */
    List<RegulationPo> findListFromIds(String ids, String delimiter);

    /**
     * 通过因子id获取VoList
     *
     * @param factorId
     * @return
     */
    List<RegulationVo> findVoListByFactorId(Long factorId);

    List<RegulationVo> findInfo(String storeCode, String period, Long factorId);

    /**
     * 删除所有数据
     *
     * @return boolean
     */
    boolean deleteAll();
}