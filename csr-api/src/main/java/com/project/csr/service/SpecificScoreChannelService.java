
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.SpecificScoreChannelPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.SpecificScoreChannelVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则-分渠道得分关系表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
public interface SpecificScoreChannelService extends IService<SpecificScoreChannelPo> {

    /**
     * 分页查询
     *
     * @param specificScoreChannelVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.SpecificScoreChannelPo>
     * @author bin.tong
     * @since 2020-11-09
     */
    IPage<SpecificScoreChannelPo> findListByPage(SpecificScoreChannelVo specificScoreChannelVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-09
     */
    boolean prohibitById(String id);

    List<Map<String, Object>> findMapList(Long storeId, String period, Long factorId);
}