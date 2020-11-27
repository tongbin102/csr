
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScoreChannelPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ScoreChannelVo;
import com.project.csr.model.vo.ScoreVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分渠道成绩统计表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ScoreChannelService extends IService<ScoreChannelPo> {

    /**
     * 分页查询
     *
     * @param scoreChannelVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScoreChannelPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ScoreChannelPo> findListByPage(ScoreChannelVo scoreChannelVo);

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
     * 获取分渠道得分信息
     *
     * @param scopeId
     * @param currentPeriod
     * @return
     */
    List<Map<String, Object>> findMapList(Long scopeId, String storeCode, String currentPeriod, String lastPeriod);

    List<Map<String, Object>> findVoMapList(Long scopeId, String storeCode, String beginPeriod, String endPeriod);

    /**
     * 通过期数删除数据
     *
     * @return boolean
     */
    boolean deleteByPeriod(String period);
}