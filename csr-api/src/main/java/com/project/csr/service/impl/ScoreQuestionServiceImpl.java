package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScoreQuestionMapper;
import com.project.csr.model.po.ScoreQuestionPo;
import com.project.csr.model.vo.ScoreQuestionVo;
import com.project.csr.service.ScoreQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门店题目-得分表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Service
public class ScoreQuestionServiceImpl extends ServiceImpl<ScoreQuestionMapper, ScoreQuestionPo> implements ScoreQuestionService {

    @Autowired
    private ScoreQuestionMapper scoreQuestionMapper;

    @Override
    public IPage<ScoreQuestionPo> findListByPage(ScoreQuestionVo scoreQuestionVo) {
        IPage<ScoreQuestionPo> page = new Page<>(scoreQuestionVo.getPageNo(), scoreQuestionVo.getPageSize());
        LambdaQueryWrapper<ScoreQuestionPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScoreQuestionPo> selectPage = scoreQuestionMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScoreQuestionPo po = new ScoreQuestionPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScoreQuestionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreQuestionPo::getId, id);
        return scoreQuestionMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<ScoreQuestionPo> findByStoreAndQuestionSeriesNos(String period, String storeCode, String channelCode, String questionSeriesNos) {
        LambdaQueryWrapper<ScoreQuestionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreQuestionPo::getPeriod, period)
                .eq(ScoreQuestionPo::getStoreCode, storeCode)
                .eq(ScoreQuestionPo::getChannelCode, channelCode)
                .in(ScoreQuestionPo::getQuestionSeriesNo, questionSeriesNos.split(","));
        return scoreQuestionMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteByPeriod(String period) {
        LambdaQueryWrapper<ScoreQuestionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScoreQuestionPo::getPeriod, period);
        return scoreQuestionMapper.delete(wrapper) >= 1;
    }
}

