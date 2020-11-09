package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.SpecificScoreChannelMapper;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.po.SpecificScoreChannelPo;
import com.project.csr.model.vo.SpecificScoreChannelVo;
import com.project.csr.service.SpecificScoreChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> findMapList(Long storeId, String period, Long factorId) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("period", period);
        params.put("factor_id", factorId);
        List<SpecificScoreChannelVo> specificScoreChannelVoList = specificScoreChannelMapper.findVoList(params);
        List<SpecificScoreChannelVo> list = new ArrayList<>();
        Long lastSpecificId = 0L;
        for (int i = 0; i < specificScoreChannelVoList.size(); i++) {
            SpecificScoreChannelVo item = specificScoreChannelVoList.get(i);
            Long specificId = item.getSpecificId();
            if (null != list && list.size() > 0 && !specificId.equals(lastSpecificId)) {
                resultList.add(convertListToMap(list));
                list.clear();
            }
            list.add(item);
            lastSpecificId = specificId;
        }
        resultList.add(convertListToMap(list));
        return resultList;
    }

    private Map<String, Object> convertListToMap(List<SpecificScoreChannelVo> list) {
        Map<String, Object> map = new HashMap<>();
        SpecificScoreChannelVo first = list.get(0);
        map.put("store_id", first.getStoreId());
        map.put("period", first.getPeriod());
        map.put("element_id", first.getElementId());
        map.put("element_name", first.getElementName());
        map.put("specific_id", first.getSpecificId());
        map.put("specific_name", first.getSpecificName());
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

