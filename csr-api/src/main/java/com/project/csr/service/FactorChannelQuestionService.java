
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.FactorChannelQuestionPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.FactorChannelQuestionVo;

/**
 * <p>
 * TSS-1 因子要素映射配置表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface FactorChannelQuestionService extends IService<FactorChannelQuestionPo> {

    /**
     * 分页查询
     *
     * @param factorChannelQuestionVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.FactorChannelQuestionPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<FactorChannelQuestionPo> findListByPage(FactorChannelQuestionVo factorChannelQuestionVo);

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