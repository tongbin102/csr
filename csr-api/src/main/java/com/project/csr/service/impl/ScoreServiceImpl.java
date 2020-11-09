package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreMapper;
import com.project.csr.model.po.ScorePo;
import com.project.csr.model.po.StorePo;
import com.project.csr.model.vo.ScoreVo;
import com.project.csr.service.ScoreService;
import com.project.csr.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private StoreService storeService;

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
    public List<ScoreVo> findScoreInfo(Integer parentId, String currentPeriod, String lastPeriod) {
        List<StorePo> childStoreList = storeService.findByParentId(parentId);
        if (null != childStoreList && childStoreList.size() > 0) {
            String childStoreIds = childStoreList.stream().map(StorePo::getId).collect(Collectors.joining(","));
            List<ScoreVo> scoreVoList =  this.findVoList(childStoreIds, currentPeriod, lastPeriod);
            log.info("aaa",scoreVoList);
            return scoreVoList;
        }
        return null;
    }

    @Override
    public List<ScoreVo> findVoList(String storeIds, String currentPeriod, String lastPeriod) {
        Map<String, Object> params = new HashMap<>();
        params.put("current_period", currentPeriod);
        params.put("last_period", lastPeriod);
        params.put("store_ids", storeIds.split(","));
        return scoreMapper.findVoList(params);
    }


}

