package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ElementScoreMapper;
import com.project.csr.model.po.ElementScorePo;
import com.project.csr.model.vo.ElementScoreVo;
import com.project.csr.service.ElementScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分项成绩统计表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class ElementScoreServiceImpl extends ServiceImpl<ElementScoreMapper, ElementScorePo> implements ElementScoreService {

    @Autowired
    private ElementScoreMapper elementScoreMapper;

    @Override
    public IPage<ElementScorePo> findListByPage(ElementScoreVo elementScoreVo) {
        IPage<ElementScorePo> page = new Page<>(elementScoreVo.getPageNo(), elementScoreVo.getPageSize());
        LambdaQueryWrapper<ElementScorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ElementScorePo> selectPage = elementScoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ElementScorePo po = new ElementScorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ElementScorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ElementScorePo::getId, id);
        return elementScoreMapper.update(po, wrapper) >= 1;
    }
}

