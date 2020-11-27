
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.po.QuestionSurveyPo;
import com.project.csr.model.vo.QuestionSurveyVo;

import java.util.List;

/**
 * <p>
 * 客户调研评分规则表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
public interface QuestionSurveyService extends IService<QuestionSurveyPo> {

    /**
     * 分页查询
     *
     * @param questionSurveyVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionSurveyPo>
     * @author bin.tong
     * @since 2020-11-10
     */
    IPage<QuestionSurveyPo> findListByPage(QuestionSurveyVo questionSurveyVo);

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
    List<QuestionSurveyVo> findListByRegulationId(String period, String storeCode, Long regulationId);

    /**
     * 删除所有数据
     *
     * @return boolean
     */
    boolean deleteAll();
}