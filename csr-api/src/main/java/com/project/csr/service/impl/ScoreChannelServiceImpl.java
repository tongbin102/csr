package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ChannelMapper;
import com.project.csr.dao.ScoreChannelMapper;
import com.project.csr.model.po.ChannelPo;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.vo.ScoreChannelVo;
import com.project.csr.service.ChannelService;
import com.project.csr.service.ScoreChannelService;
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

    @Autowired
    private ChannelService channelService;

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
    public List<Map<String, Object>> findVoList(Integer storeId, String currentPeriod, String lastPeriod) {
        List<Map<String, Object>> resultList = new ArrayList<>();

        LambdaQueryWrapper<ScoreChannelPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreChannelPo::getStoreId, storeId)
                .in(ScoreChannelPo::getPeriod, currentPeriod);
        List<ScoreChannelPo> currentList = scoreChannelMapper.selectList(wrapper);
        Map<String, Object> currentMap = convertListToMap(currentList);
        currentMap.put("period", "本期");
        resultList.add(currentMap);

        wrapper.clear();

        wrapper.eq(ScoreChannelPo::getStoreId, storeId)
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
            map.put(scoreChannelPo.getChannelId().toString(), scoreChannelPo.getScore());
        }
        return map;
    }
}

