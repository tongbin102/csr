package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RegulationScoreChannelMapper;
import com.project.csr.model.po.RegulationScoreChannelPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;
import com.project.csr.service.ChannelService;
import com.project.csr.service.RegulationScoreChannelService;
import com.project.csr.service.RegulationService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 因子要素细则分渠道评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Service
public class RegulationScoreChannelServiceImpl extends ServiceImpl<RegulationScoreChannelMapper, RegulationScoreChannelPo> implements RegulationScoreChannelService {

    @Autowired
    private RegulationScoreChannelMapper regulationScoreChannelMapper;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private RegulationService regulationService;

    @Override
    public IPage<RegulationScoreChannelPo> findListByPage(RegulationScoreChannelVo regulationScoreChannelVo) {
        IPage<RegulationScoreChannelPo> page = new Page<>(regulationScoreChannelVo.getPageNo(), regulationScoreChannelVo.getPageSize());
        LambdaQueryWrapper<RegulationScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RegulationScoreChannelPo> selectPage = regulationScoreChannelMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RegulationScoreChannelPo po = new RegulationScoreChannelPo();
        po.setValidInd(false);
        LambdaQueryWrapper<RegulationScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationScoreChannelPo::getId, id);
        return regulationScoreChannelMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<RegulationScoreChannelVo> findVoList(Long storeId, String period, Long factorId, Integer channelType) {
        LambdaQueryWrapper<RegulationScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationScoreChannelPo::getStoreId, storeId)
                .eq(RegulationScoreChannelPo::getPeriod, period);
        if (null != factorId) {
            String regulationIds = ToolsUtils.getIdsFromList(regulationService.findVoListByFactorId(factorId), ",");
            wrapper.in(RegulationScoreChannelPo::getRegulationId, regulationIds.split(","));
        }
        if (null != channelType) {
            String channelIds = ToolsUtils.getIdsFromList(channelService.findListByCtype(channelType), ",");
            wrapper.in(RegulationScoreChannelPo::getChannelId, channelIds.split(","));
        }
        return ConvertUtils.convert(regulationScoreChannelMapper.selectList(wrapper), RegulationScoreChannelVo.class);
    }

}

