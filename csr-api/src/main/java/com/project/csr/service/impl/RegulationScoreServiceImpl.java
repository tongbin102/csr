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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<RegulationScoreVo> findVoList(Long storeId, String period, Long factorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("period", period);
        params.put("factor_id", factorId);
        return regulationScoreMapper.findVoList(params);
    }

    @Override
    public List<RegulationScoreVo> findInfo(Long storeId, String period, Long factorId) {
        List<RegulationScoreVo> regulationScoreVoList = this.findVoList(storeId, period, factorId);
        List<Map<String, Object>> mapList = regulationScoreChannelService.findMapList(storeId, period, factorId);
        regulationScoreVoList.stream().forEach(item -> {
            item.setRegulationScoreChannelMap(getRegulationScoreMap(item.getRegulationId(), mapList));
        });
        return regulationScoreVoList;
    }

    private Map<String, Object> getRegulationScoreMap(Long regulationId, List<Map<String, Object>> mapList) {
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            if (map.get("regulation_id").equals(regulationId)) {
                return map;
            }
        }
        return null;
    }
}

