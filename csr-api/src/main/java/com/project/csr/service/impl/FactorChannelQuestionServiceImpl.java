package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.FactorChannelQuestionMapper;
import com.project.csr.model.po.FactorChannelQuestionPo;
import com.project.csr.model.vo.FactorChannelQuestionVo;
import com.project.csr.service.FactorChannelQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * TSS-1 因子要素映射配置表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class FactorChannelQuestionServiceImpl extends ServiceImpl<FactorChannelQuestionMapper, FactorChannelQuestionPo> implements FactorChannelQuestionService {

    @Autowired
    private FactorChannelQuestionMapper factorChannelQuestionMapper;

    @Override
    public IPage<FactorChannelQuestionPo> findListByPage(FactorChannelQuestionVo factorChannelQuestionVo) {
        IPage<FactorChannelQuestionPo> page = new Page<>(factorChannelQuestionVo.getPageNo(), factorChannelQuestionVo.getPageSize());
        LambdaQueryWrapper<FactorChannelQuestionPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<FactorChannelQuestionPo> selectPage = factorChannelQuestionMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        FactorChannelQuestionPo po = new FactorChannelQuestionPo();
        po.setValidInd(false);
        LambdaQueryWrapper<FactorChannelQuestionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(FactorChannelQuestionPo::getId, id);
        return factorChannelQuestionMapper.update(po, wrapper) >= 1;
    }
}

