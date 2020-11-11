package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RegulationScoreChannelMapper;
import com.project.csr.model.po.RegulationScoreChannelPo;
import com.project.csr.model.vo.RegulationScoreChannelVo;
import com.project.csr.service.RegulationScoreChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> findMapList(Long storeId, String period, Long factorId) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("period", period);
        params.put("factor_id", factorId);
        List<RegulationScoreChannelVo> regulationScoreChannelVoList = regulationScoreChannelMapper.findVoList(params);
        List<RegulationScoreChannelVo> list = new ArrayList<>();
        Long lastRegulationId = 0L;
        for (int i = 0; i < regulationScoreChannelVoList.size(); i++) {
            RegulationScoreChannelVo item = regulationScoreChannelVoList.get(i);
            Long regulationId = item.getRegulationId();
            if (null != list && list.size() > 0 && !regulationId.equals(lastRegulationId)) {
                resultList.add(convertListToMap(list));
                list.clear();
            }
            list.add(item);
            lastRegulationId = regulationId;
        }
        resultList.add(convertListToMap(list));
        return resultList;
    }

    private Map<String, Object> convertListToMap(List<RegulationScoreChannelVo> list) {
        Map<String, Object> map = new HashMap<>();
        RegulationScoreChannelVo first = list.get(0);
        map.put("store_id", first.getStoreId());
        map.put("period", first.getPeriod());
        map.put("element_id", first.getElementId());
        map.put("element_name", first.getElementName());
        map.put("regulation_id", first.getRegulationId());
        map.put("regulation_description", first.getRegulationDescription());
        list.stream().forEach(item -> {
            if (item.getScoreType() == 1) {
                map.put("basic" + item.getChannelId().toString(), item.getGrade());
            }
            if (item.getScoreType() == 2) {
                map.put("extra" + item.getChannelId().toString(), item.getGrade());
            }
        });
        return map;
    }
}

