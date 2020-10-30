package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ElementScoreChannelMapper;
import com.project.csr.model.po.ElementScoreChannelPo;
import com.project.csr.model.vo.ElementScoreChannelVo;
import com.project.csr.service.ElementScoreChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分渠道因子要素分布表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class ElementScoreChannelServiceImpl extends ServiceImpl<ElementScoreChannelMapper, ElementScoreChannelPo> implements ElementScoreChannelService {

    @Autowired
    private ElementScoreChannelMapper elementScoreChannelMapper;

    @Override
    public IPage<ElementScoreChannelPo> findListByPage(ElementScoreChannelVo elementScoreChannelVo) {
        IPage<ElementScoreChannelPo> page = new Page<>(elementScoreChannelVo.getPageNo(), elementScoreChannelVo.getPageSize());
        LambdaQueryWrapper<ElementScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ElementScoreChannelPo> selectPage = elementScoreChannelMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ElementScoreChannelPo po = new ElementScoreChannelPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ElementScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ElementScoreChannelPo::getId, id);
        return elementScoreChannelMapper.update(po, wrapper) >= 1;
    }
}

