package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ChannelMapper;
import com.project.csr.model.po.ChannelPo;
import com.project.csr.model.vo.ChannelVo;
import com.project.csr.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, ChannelPo> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public IPage<ChannelPo> findListByPage(ChannelVo channelVo) {
        IPage<ChannelPo> page = new Page<>(channelVo.getPageNo(), channelVo.getPageSize());
        LambdaQueryWrapper<ChannelPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ChannelPo> selectPage = channelMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ChannelPo po = new ChannelPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ChannelPo::getId, id);
        return channelMapper.update(po, wrapper) >= 1;
    }
}

