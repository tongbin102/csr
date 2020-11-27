package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreChannelMapper;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.vo.ScoreChannelVo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreChannelService;
import com.project.csr.service.ScoreService;
import com.project.csr.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 分渠道成绩统计表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Service
public class ScoreChannelServiceImpl extends ServiceImpl<ScoreChannelMapper, ScoreChannelPo> implements ScoreChannelService {

    @Autowired
    private ScoreChannelMapper scoreChannelMapper;

    @Autowired
    private ScoreService scoreService;

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

    @Override
    public List<Map<String, Object>> findMapList(Long scopeId, String storeCode, String currentPeriod, String lastPeriod) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreChannelPo::getScopeId, scopeId)
                .eq(ScoreChannelPo::getStoreCode, storeCode)
                .eq(ScoreChannelPo::getPeriod, currentPeriod);
        List<ScoreChannelPo> currentList = scoreChannelMapper.selectList(wrapper);
        Map<String, Object> currentMap = convertListToMap(currentList);
        currentMap.put("period", "本期");
        resultList.add(currentMap);

        wrapper.clear();

        wrapper.eq(ScoreChannelPo::getScopeId, scopeId)
                .eq(ScoreChannelPo::getStoreCode, storeCode)
                .eq(ScoreChannelPo::getPeriod, lastPeriod);
        List<ScoreChannelPo> lastList = scoreChannelMapper.selectList(wrapper);
        Map<String, Object> lastMap = convertListToMap(lastList);
        lastMap.put("period", "上期");
        resultList.add(lastMap);
        return resultList;
    }

    private Map<String, Object> convertListToMap(List<ScoreChannelPo> scoreChannelPoList) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < scoreChannelPoList.size(); i++) {
            ScoreChannelPo scoreChannelPo = scoreChannelPoList.get(i);
            map.put(scoreChannelPo.getChannelCode(), scoreChannelPo.getScore());
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> findVoMapList(Long scopeId, String storeCode, String beginPeriod, String endPeriod) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            List<ScoreVo> scoreVoList = scoreService.findVoListByPeriods(scopeId, storeCode, beginPeriod, endPeriod);

            LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(ScoreChannelPo::getScopeId, scopeId)
                    .eq(ScoreChannelPo::getStoreCode, storeCode)
                    .between(ScoreChannelPo::getPeriod, beginPeriod, endPeriod);
            List<ScoreChannelPo> scoreChannelPoList = scoreChannelMapper.selectList(wrapper);
            Map<String, Map<String, String>> scoreChannelPoMap = scoreChannelPoList.stream().collect(Collectors.groupingBy(ScoreChannelPo::getPeriod, Collectors.toMap(ScoreChannelPo::getChannelCode, ScoreChannelPo::getScore)));

            for (String strDate = beginPeriod; strDate.compareTo(endPeriod) <= 0; strDate = DateUtils.getMonth(strDate, 1, "yyyyMM")) {
                Map<String, Object> map = new HashMap<>();
                map.put("period", strDate);

                String tmpDate = strDate;
                List<ScoreVo> scoreFilterList = scoreVoList.stream().filter(s -> s.getPeriod().equals(tmpDate)).collect(Collectors.toList());
                if (null != scoreFilterList && scoreFilterList.size() > 0) {
                    map.put("score", scoreFilterList.get(0).getScore());
                } else {
                    map.put("score", "");
                }

                Map<String, String> scoreChannelMap = scoreChannelPoMap.get(strDate);
                if (null != scoreChannelMap) {
                    map.put("scoreChannel", scoreChannelMap);
                } else {
                    map.put("scoreChannel", new ArrayList<>());
                }

                resultList.add(map);
            }
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        return resultList;
    }

    @Override
    public boolean deleteByPeriod(String period) {
        LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreChannelPo::getPeriod, period);
        return scoreChannelMapper.delete(wrapper) >= 1;
    }

}

