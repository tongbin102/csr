package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.QuestionDataMapper;
import com.project.csr.model.po.QuestionDataPo;
import com.project.csr.model.vo.QuestionDataVo;
import com.project.csr.service.QuestionDataService;
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
public class QuestionDataServiceImpl extends ServiceImpl<QuestionDataMapper, QuestionDataPo> implements QuestionDataService {

    @Autowired
    private QuestionDataMapper questionDataMapper;

    @Override
    public IPage<QuestionDataPo> findListByPage(QuestionDataVo questionDataVo) {
        IPage<QuestionDataPo> page = new Page<>(questionDataVo.getPageNo(), questionDataVo.getPageSize());
        LambdaQueryWrapper<QuestionDataPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<QuestionDataPo> selectPage = questionDataMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        QuestionDataPo po = new QuestionDataPo();
        po.setValidInd(false);
        LambdaQueryWrapper<QuestionDataPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(QuestionDataPo::getId, id);
        return questionDataMapper.update(po, wrapper) >= 1;
    }
}

