package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.SpecificScoreChannelMapper;
import com.project.csr.model.po.SpecificScoreChannelPo;
import com.project.csr.model.vo.SpecificScoreChannelVo;
import com.project.csr.service.SpecificScoreChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 细则-分渠道得分关系表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
@Service
public class SpecificScoreChannelServiceImpl extends ServiceImpl<SpecificScoreChannelMapper, SpecificScoreChannelPo> implements SpecificScoreChannelService {

    @Autowired
    private SpecificScoreChannelMapper specificScoreChannelMapper;

    @Override
    public IPage<SpecificScoreChannelPo> findListByPage(SpecificScoreChannelVo specificScoreChannelVo) {
        IPage<SpecificScoreChannelPo> page = new Page<>(specificScoreChannelVo.getPageNo(), specificScoreChannelVo.getPageSize());
        LambdaQueryWrapper<SpecificScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<SpecificScoreChannelPo> selectPage = specificScoreChannelMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        SpecificScoreChannelPo po = new SpecificScoreChannelPo();
        po.setValidInd(false);
        LambdaQueryWrapper<SpecificScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SpecificScoreChannelPo::getId, id);
        return specificScoreChannelMapper.update(po, wrapper) >= 1;
    }
}

