package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionSurveyMapper;
import com.project.csr.model.po.QuestionSurveyPo;
import com.project.csr.model.vo.QuestionSurveyVo;
import com.project.csr.service.QuestionSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * TSS-2 调研问卷评分规则配置表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class QuestionSurveyServiceImpl extends ServiceImpl<QuestionSurveyMapper, QuestionSurveyPo> implements QuestionSurveyService {

    @Autowired
    private QuestionSurveyMapper questionSurveyMapper;

    @Override
    public IPage<QuestionSurveyPo> findListByPage(QuestionSurveyVo questionSurveyVo) {
        IPage<QuestionSurveyPo> page = new Page<>(questionSurveyVo.getPageNo(), questionSurveyVo.getPageSize());
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionSurveyPo> selectPage = questionSurveyMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionSurveyPo po = new QuestionSurveyPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionSurveyPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionSurveyPo::getId, id);
        return questionSurveyMapper.update(po, wrapper) >= 1;
    }
}

