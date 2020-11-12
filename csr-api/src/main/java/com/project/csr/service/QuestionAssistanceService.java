
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.QuestionAssistancePo;
import com.project.csr.model.vo.QuestionAssistanceVo;

import java.util.List;

/**
 * <p>
 * 服务助手评分规则表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
public interface QuestionAssistanceService extends IService<QuestionAssistancePo> {

    /**
     * 分页查询
     *
     * @param questionAssistanceVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionAssistancePo>
     * @author bin.tong
     * @since 2020-11-10
     */
    IPage<QuestionAssistancePo> findListByPage(QuestionAssistanceVo questionAssistanceVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-10
     */
    boolean prohibitById(String id);

    /**
     * 通过因子要素细则id获取列表
     *
     * @param regulationId
     * @return
     */
    List<QuestionAssistanceVo> findListByRegulationId(String period, Long storeId, Long regulationId);
}