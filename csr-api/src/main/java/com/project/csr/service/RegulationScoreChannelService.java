
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.RegulationScoreChannelPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;

import java.util.List;

/**
 * <p>
 * 因子要素细则分渠道评分规则表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
public interface RegulationScoreChannelService extends IService<RegulationScoreChannelPo> {

    /**
     * 分页查询
     *
     * @param regulationScoreChannelVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RegulationScoreChannelPo>
     * @author bin.tong
     * @since 2020-11-11
     */
    IPage<RegulationScoreChannelPo> findListByPage(RegulationScoreChannelVo regulationScoreChannelVo);

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
     * 通过regulationsIds查询列表
     *
     * @param storeId
     * @param period
     * @param regulationIds
     * @return
     */
    List<RegulationScoreChannelVo> findVoList(Long storeId, String period, String regulationIds);

}