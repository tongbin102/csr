package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreFactorMapper;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.vo.ScoreFactorVo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.service.ScoreService;
import com.project.csr.utils.DateUtils;
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
 * 分因子成绩统计表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class ScoreFactorServiceImpl extends ServiceImpl<ScoreFactorMapper, ScoreFactorPo> implements ScoreFactorService {

    @Autowired
    private ScoreFactorMapper scoreFactorMapper;

    @Autowired
    private ScoreService scoreService;

    @Override
    public IPage<ScoreFactorPo> findListByPage(ScoreFactorVo scoreFactorVo) {
        IPage<ScoreFactorPo> page = new Page<>(scoreFactorVo.getPageNo(), scoreFactorVo.getPageSize());
        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScoreFactorPo> selectPage = scoreFactorMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScoreFactorPo po = new ScoreFactorPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreFactorPo::getId, id);
        return scoreFactorMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<Map<String, Object>> findMapList(Long scopeId, Long storeId, String currentPeriod, String lastPeriod) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreFactorPo::getScopeId, scopeId)
                .eq(ScoreFactorPo::getStoreId, storeId)
                .eq(ScoreFactorPo::getPeriod, currentPeriod);
        List<ScoreFactorPo> currentList = scoreFactorMapper.selectList(wrapper);
        Map<String, Object> currentMap = convertListToMap(currentList);
        currentMap.put("period", "本期");
        resultList.add(currentMap);

        wrapper.clear();

        wrapper.eq(ScoreFactorPo::getScopeId, scopeId)
                .eq(ScoreFactorPo::getStoreId, storeId)
                .eq(ScoreFactorPo::getPeriod, lastPeriod);
        List<ScoreFactorPo> lastList = scoreFactorMapper.selectList(wrapper);
        Map<String, Object> lastMap = convertListToMap(lastList);
        lastMap.put("period", "上期");
        resultList.add(lastMap);
        return resultList;
    }

    private Map<String, Object> convertListToMap(List<ScoreFactorPo> scoreFactorPoList) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < scoreFactorPoList.size(); i++) {
            ScoreFactorPo scoreFactorPo = scoreFactorPoList.get(i);
            map.put(scoreFactorPo.getFactorId().toString(), scoreFactorPo.getScore());
        }
        return map;
    }

    @Override
    public List<ScoreFactorVo> findVoList(Map<String, Object> params) {
        return scoreFactorMapper.findVoList(params);
    }


    @Override
    public List<Map<String, Object>> findVoMapList(Long scopeId, Long storeId, String beginPeriod, String endPeriod) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            List<ScoreVo> scoreVoList = scoreService.findVoListByPeriods(scopeId, storeId, beginPeriod, endPeriod);

            LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(ScoreFactorPo::getScopeId, scopeId)
                    .eq(ScoreFactorPo::getStoreId, storeId)
                    .between(ScoreFactorPo::getPeriod, beginPeriod, endPeriod);
            List<ScoreFactorPo> scoreFactorPoList = scoreFactorMapper.selectList(wrapper);
            Map<String, Map<Long, String>> scoreFactorPoMap = scoreFactorPoList.stream().collect(Collectors.groupingBy(ScoreFactorPo::getPeriod, Collectors.toMap(ScoreFactorPo::getFactorId, ScoreFactorPo::getScore)));

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

                Map<Long, String> scoreFactorMap = scoreFactorPoMap.get(strDate);
                if (null != scoreFactorMap) {
                    map.put("scoreFactor", scoreFactorMap);
                } else {
                    map.put("scoreFactor", new ArrayList<>());
                }

                resultList.add(map);
            }
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        return resultList;
    }
}

