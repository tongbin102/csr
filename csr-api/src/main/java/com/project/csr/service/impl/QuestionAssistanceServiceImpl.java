package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionAssistanceMapper;
import com.project.csr.model.po.QuestionAssistancePo;
import com.project.csr.model.vo.QuestionAssistanceVo;
import com.project.csr.service.QuestionAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务助手评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Service
public class QuestionAssistanceServiceImpl extends ServiceImpl<QuestionAssistanceMapper, QuestionAssistancePo> implements QuestionAssistanceService {

    @Autowired
    private QuestionAssistanceMapper questionAssistanceMapper;

    @Override
    public IPage<QuestionAssistancePo> findListByPage(QuestionAssistanceVo questionAssistanceVo) {
        IPage<QuestionAssistancePo> page = new Page<>(questionAssistanceVo.getPageNo(), questionAssistanceVo.getPageSize());
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionAssistancePo> selectPage = questionAssistanceMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionAssistancePo po = new QuestionAssistancePo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionAssistancePo::getId, id);
        return questionAssistanceMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionAssistancePo> findListByRegulationId(Long regulationId) {
        LambdaQueryWrapper<QuestionAssistancePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionAssistancePo::getRegulationId, regulationId);
        return questionAssistanceMapper.selectList(wrapper);
    }
}

