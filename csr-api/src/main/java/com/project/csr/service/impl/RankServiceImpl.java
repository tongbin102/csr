package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RankMapper;
import com.project.csr.model.po.RankPo;
import com.project.csr.model.vo.RankVo;
import com.project.csr.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 成绩排名表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class RankServiceImpl extends ServiceImpl<RankMapper, RankPo> implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public IPage<RankPo> findListByPage(RankVo rankVo) {
        IPage<RankPo> page = new Page<>(rankVo.getPageNo(), rankVo.getPageSize());
        LambdaQueryWrapper<RankPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RankPo> selectPage = rankMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RankPo po = new RankPo();
        po.setValidInd(false);
        LambdaQueryWrapper<RankPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RankPo::getId, id);
        return rankMapper.update(po, wrapper) >= 1;
    }
}

