package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreFactorMapper;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.vo.ScoreFactorVo;
import com.project.csr.service.ScoreFactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分因子成绩统计表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class ScoreFactorServiceImpl extends ServiceImpl<ScoreFactorMapper, ScoreFactorPo> implements ScoreFactorService {

    @Autowired
    private ScoreFactorMapper scoreFactorMapper;

    @Override
    public IPage<ScoreFactorPo> findListByPage(ScoreFactorVo scoreFactorVo) {
        IPage<ScoreFactorPo> page = new Page<>(scoreFactorVo.getPageNo(), scoreFactorVo.getPageSize());
        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScoreFactorPo> selectPage = scoreFactorMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScoreFactorPo po = new ScoreFactorPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreFactorPo::getId, id);
        return scoreFactorMapper.update(po, wrapper) >= 1;
    }
}

