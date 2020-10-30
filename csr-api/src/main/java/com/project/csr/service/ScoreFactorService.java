
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScoreFactorPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ScoreFactorVo;

/**
 * <p>
 * 分因子成绩统计表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface ScoreFactorService extends IService<ScoreFactorPo> {

    /**
     * 分页查询
     *
     * @param scoreFactorVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScoreFactorPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<ScoreFactorPo> findListByPage(ScoreFactorVo scoreFactorVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-10-30
     */
    boolean prohibitById(String id);
}