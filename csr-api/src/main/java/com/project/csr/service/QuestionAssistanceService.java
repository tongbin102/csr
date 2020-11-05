
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionAssistancePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.QuestionAssistanceVo;

/**
 * <p>
 * TSS-4 服务助手及道路救援 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface QuestionAssistanceService extends IService<QuestionAssistancePo> {

    /**
     * 分页查询
     *
     * @param questionAssistanceVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionAssistancePo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<QuestionAssistancePo> findListByPage(QuestionAssistanceVo questionAssistanceVo);

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