package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionMonitorMapper;
import com.project.csr.model.po.QuestionMonitorPo;
import com.project.csr.model.vo.QuestionMonitorVo;
import com.project.csr.service.QuestionMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 过程监控评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-10
 */
@Service
public class QuestionMonitorServiceImpl extends ServiceImpl<QuestionMonitorMapper, QuestionMonitorPo> implements QuestionMonitorService {

    @Autowired
    private QuestionMonitorMapper questionMonitorMapper;

    @Override
    public IPage<QuestionMonitorPo> findListByPage(QuestionMonitorVo questionMonitorVo) {
        IPage<QuestionMonitorPo> page = new Page<>(questionMonitorVo.getPageNo(), questionMonitorVo.getPageSize());
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionMonitorPo> selectPage = questionMonitorMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionMonitorPo po = new QuestionMonitorPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionMonitorPo::getId, id);
        return questionMonitorMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<QuestionMonitorPo> findListByRegulationId(Long regulationId) {
        LambdaQueryWrapper<QuestionMonitorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionMonitorPo::getRegulationId, regulationId);
        return questionMonitorMapper.selectList(wrapper);
    }
}

