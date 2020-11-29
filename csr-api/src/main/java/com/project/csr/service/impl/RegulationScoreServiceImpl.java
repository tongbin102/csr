package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RegulationScoreMapper;
import com.project.csr.model.po.RegulationScorePo;
import com.project.csr.model.vo.RegulationScoreVo;
import com.project.csr.service.RegulationScoreChannelService;
import com.project.csr.service.RegulationScoreService;
import com.project.csr.service.RegulationService;
import com.project.csr.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 因子要素细则评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Service
public class RegulationScoreServiceImpl extends ServiceImpl<RegulationScoreMapper, RegulationScorePo> implements RegulationScoreService {

    @Autowired
    private RegulationScoreMapper regulationScoreMapper;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private RegulationScoreChannelService regulationScoreChannelService;

    @Override
    public IPage<RegulationScorePo> findListByPage(RegulationScoreVo regulationScoreVo) {
        IPage<RegulationScorePo> page = new Page<>(regulationScoreVo.getPageNo(), regulationScoreVo.getPageSize());
        LambdaQueryWrapper<RegulationScorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RegulationScorePo> selectPage = regulationScoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RegulationScorePo po = new RegulationScorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<RegulationScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationScorePo::getId, id);
        return regulationScoreMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<RegulationScoreVo> findVoList(String storeCode, String period, String regulationDescriptions) {
        LambdaQueryWrapper<RegulationScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationScorePo::getStoreCode, storeCode)
                .eq(RegulationScorePo::getPeriod, period)
                .in(RegulationScorePo::getRegulationDescription, regulationDescriptions.split(","));
        return ConvertUtils.convert(regulationScoreMapper.selectList(wrapper), RegulationScoreVo.class);
    }

    @Override
    public boolean deleteByPeriod(String period) {
        LambdaQueryWrapper<RegulationScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationScorePo::getPeriod, period);
        return regulationScoreMapper.delete(wrapper) >= 1;
    }
}

