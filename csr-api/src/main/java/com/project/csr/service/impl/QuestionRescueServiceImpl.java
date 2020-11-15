package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionRescueMapper;
import com.project.csr.model.po.QuestionRescuePo;
import com.project.csr.model.vo.QuestionRescueVo;
import com.project.csr.service.QuestionRescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 道路救援评分规则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-15
 */
@Service
public class QuestionRescueServiceImpl extends ServiceImpl<QuestionRescueMapper, QuestionRescuePo> implements QuestionRescueService {

    @Autowired
    private QuestionRescueMapper questionRescueMapper;

    @Override
    public IPage<QuestionRescuePo> findListByPage(QuestionRescueVo questionRescueVo) {
        IPage<QuestionRescuePo> page = new Page<>(questionRescueVo.getPageNo(), questionRescueVo.getPageSize());
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionRescuePo> selectPage = questionRescueMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionRescuePo po = new QuestionRescuePo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionRescuePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionRescuePo::getId, id);
        return questionRescueMapper.update(po, wrapper) >= 1;
    }
}

