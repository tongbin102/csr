
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.RegulationScorePo;
import com.project.csr.model.vo.RegulationScoreVo;

import java.util.List;

/**
 * <p>
 * 因子要素细则评分规则表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
public interface RegulationScoreService extends IService<RegulationScorePo> {

    /**
     * 分页查询
     *
     * @param regulationScoreVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RegulationScorePo>
     * @author bin.tong
     * @since 2020-11-11
     */
    IPage<RegulationScorePo> findListByPage(RegulationScoreVo regulationScoreVo);

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
     * 通过regulationIds获取列表
     *
     * @param storeCode
     * @param period
     * @param regulationDescriptions
     * @return
     */
    List<RegulationScoreVo> findVoList(String storeCode, String period, String regulationDescriptions);

    /**
     * 通过期数删除数据
     *
     * @return boolean
     */
    boolean deleteByPeriod(String period);

}