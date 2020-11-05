package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreChannelMapper;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.vo.ScoreChannelVo;
import com.project.csr.service.ScoreChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分渠道成绩统计表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class ScoreChannelServiceImpl extends ServiceImpl<ScoreChannelMapper, ScoreChannelPo> implements ScoreChannelService {

    @Autowired
    private ScoreChannelMapper scoreChannelMapper;

    @Override
    public IPage<ScoreChannelPo> findListByPage(ScoreChannelVo scoreChannelVo) {
        IPage<ScoreChannelPo> page = new Page<>(scoreChannelVo.getPageNo(), scoreChannelVo.getPageSize());
        LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScoreChannelPo> selectPage = scoreChannelMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScoreChannelPo po = new ScoreChannelPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreChannelPo::getId, id);
        return scoreChannelMapper.update(po, wrapper) >= 1;
    }
}

