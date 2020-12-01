package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionComplainMapper;
import com.project.csr.model.po.QuestionComplainPo;
import com.project.csr.model.vo.QuestionComplainVo;
import com.project.csr.service.QuestionComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投诉单表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-01
 */
@Service
public class QuestionComplainServiceImpl extends ServiceImpl<QuestionComplainMapper, QuestionComplainPo> implements QuestionComplainService {

    @Autowired
    private QuestionComplainMapper questionComplainMapper;

    @Override
    public IPage<QuestionComplainPo> findListByPage(QuestionComplainVo questionComplainVo) {
        IPage<QuestionComplainPo> page = new Page<>(questionComplainVo.getPageNo(), questionComplainVo.getPageSize());
        LambdaQueryWrapper<QuestionComplainPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionComplainPo> selectPage = questionComplainMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionComplainPo po = new QuestionComplainPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionComplainPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionComplainPo::getId, id);
        return questionComplainMapper.update(po, wrapper) >= 1;
    }

    @Override
    public boolean deleteByPeriod(String period) {
        LambdaQueryWrapper<QuestionComplainPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionComplainPo::getPeriod, period);
        return questionComplainMapper.delete(wrapper) >= 1;
    }
}

