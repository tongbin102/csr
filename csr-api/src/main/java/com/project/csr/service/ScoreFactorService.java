
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.vo.ScoreFactorVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分因子成绩统计表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ScoreFactorService extends IService<ScoreFactorPo> {

    /**
     * 分页查询
     *
     * @param scoreFactorVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScoreFactorPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ScoreFactorPo> findListByPage(ScoreFactorVo scoreFactorVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-05
     */
    boolean prohibitById(String id);

    /**
     * 获取分因子得分信息
     *
     * @param storeCode
     * @param currentPeriod
     * @param lastPeriod
     * @return
     */
    List<Map<String, Object>> findMapList(Long scopeId, String storeCode, String currentPeriod, String lastPeriod);

    List<ScoreFactorVo> findVoList(Map<String, Object> params);

    List<Map<String, Object>> findVoMapList(Long scopeId, String storeCode, String beginPeriod, String endPeriod);

    /**
     * 通过期数删除数据
     *
     * @return boolean
     */
    boolean deleteByPeriod(String period);
}