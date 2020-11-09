
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.SpecificScorePo;
import com.project.csr.model.vo.SpecificScoreVo;

import java.util.List;

/**
 * <p>
 * 细则-分数关系表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
public interface SpecificScoreService extends IService<SpecificScorePo> {

    /**
     * 分页查询
     *
     * @param specificScoreVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.SpecificScorePo>
     * @author bin.tong
     * @since 2020-11-09
     */
    IPage<SpecificScorePo> findListByPage(SpecificScoreVo specificScoreVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-09
     */
    boolean prohibitById(String id);

    /**
     * @param storeId
     * @param period
     * @param factorId
     * @return
     */
    List<SpecificScoreVo> findVoList(Long storeId, String period, Long factorId);

    List<SpecificScoreVo> findInfo(Long storeId, String period, Long factorId);
}