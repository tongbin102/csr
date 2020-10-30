
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RankPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.RankVo;

/**
 * <p>
 * 成绩排名表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface RankService extends IService<RankPo> {

    /**
     * 分页查询
     *
     * @param rankVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RankPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<RankPo> findListByPage(RankVo rankVo);

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