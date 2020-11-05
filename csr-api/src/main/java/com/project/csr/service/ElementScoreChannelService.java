
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ElementScoreChannelPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ElementScoreChannelVo;

/**
 * <p>
 * 分渠道因子要素分布表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ElementScoreChannelService extends IService<ElementScoreChannelPo> {

    /**
     * 分页查询
     *
     * @param elementScoreChannelVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ElementScoreChannelPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ElementScoreChannelPo> findListByPage(ElementScoreChannelVo elementScoreChannelVo);

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