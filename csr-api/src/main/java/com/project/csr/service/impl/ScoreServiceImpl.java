package com.project.csr.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreMapper;
import com.project.csr.model.po.ScoreChannelPo;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 成绩排名表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Slf4j
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, ScorePo> implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public IPage<ScorePo> findListByPage(ScoreVo scoreVo) {
        IPage<ScorePo> page = new Page<>(scoreVo.getPageNo(), scoreVo.getPageSize());
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScorePo> selectPage = scoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScorePo po = new ScorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScorePo::getId, id);
        return scoreMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<ScorePo> findVoList(Integer scopeId, String period, String storeIds) {

        LambdaQueryWrapper<ScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScorePo::getScopeId, scopeId)
                .eq(ScorePo::getPeriod, period)
                .in(ScorePo::getStoreId, storeIds);
        return scoreMapper.selectList(wrapper);
    }
}

