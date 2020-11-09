package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.SpecificScoreMapper;
import com.project.csr.model.po.SpecificScorePo;
import com.project.csr.model.vo.SpecificScoreVo;
import com.project.csr.service.SpecificScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 细则-分数关系表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
@Service
public class SpecificScoreServiceImpl extends ServiceImpl<SpecificScoreMapper, SpecificScorePo> implements SpecificScoreService {

    @Autowired
    private SpecificScoreMapper specificScoreMapper;

    @Override
    public IPage<SpecificScorePo> findListByPage(SpecificScoreVo specificScoreVo) {
        IPage<SpecificScorePo> page = new Page<>(specificScoreVo.getPageNo(), specificScoreVo.getPageSize());
        LambdaQueryWrapper<SpecificScorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<SpecificScorePo> selectPage = specificScoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        SpecificScorePo po = new SpecificScorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<SpecificScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SpecificScorePo::getId, id);
        return specificScoreMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<SpecificScoreVo> findVoList(Long storeId, String period, Long factorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("period", period);
        params.put("factor_id", factorId);
        return specificScoreMapper.findVoList(params);
    }
}

