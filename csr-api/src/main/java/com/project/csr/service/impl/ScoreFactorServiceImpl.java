package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreFactorMapper;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.po.ScoreFactorPo;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.vo.ScoreFactorVo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreFactorService;
import com.project.csr.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> findVoList(Integer scopeId, String currentPeriod, Integer storeId) throws ParseException {
        List<Map<String, Object>> resultList = new ArrayList<>();

        LambdaQueryWrapper<ScoreFactorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreFactorPo::getScopeId, scopeId)
                .in(ScoreFactorPo::getPeriod, currentPeriod);
        List<ScoreFactorPo> currentList = scoreFactorMapper.selectList(wrapper);
        Map<String, Object> currentMap = convertListToMap(currentList);
        currentMap.put("period", currentPeriod);
        resultList.add(currentMap);

        wrapper.clear();

        String lastPeriod = DateUtils.getMonth(currentPeriod, -1, "yyyyMM");
        wrapper.eq(ScoreFactorPo::getScopeId, scopeId)
                .in(ScoreFactorPo::getPeriod, lastPeriod);
        List<ScoreFactorPo> lastList = scoreFactorMapper.selectList(wrapper);
        Map<String, Object> lastMap = convertListToMap(lastList);
        lastMap.put("period", lastPeriod);
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

}

