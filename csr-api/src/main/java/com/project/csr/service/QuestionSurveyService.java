
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionSurveyPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.QuestionSurveyVo;

/**
 * <p>
 * TSS-2 调研问卷评分规则配置表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface QuestionSurveyService extends IService<QuestionSurveyPo> {

    /**
     * 分页查询
     *
     * @param questionSurveyVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionSurveyPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<QuestionSurveyPo> findListByPage(QuestionSurveyVo questionSurveyVo);

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