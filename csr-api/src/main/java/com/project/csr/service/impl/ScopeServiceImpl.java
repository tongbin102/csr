package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ScopeMapper;
import com.project.csr.model.po.ScopePo;
import com.project.csr.model.vo.ScopeVo;
import com.project.csr.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 范围表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class ScopeServiceImpl extends ServiceImpl<ScopeMapper, ScopePo> implements ScopeService {

    @Autowired
    private ScopeMapper scopeMapper;

    @Override
    public IPage<ScopePo> findListByPage(ScopeVo scopeVo) {
        IPage<ScopePo> page = new Page<>(scopeVo.getPageNo(), scopeVo.getPageSize());
        LambdaQueryWrapper<ScopePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ScopePo> selectPage = scopeMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ScopePo po = new ScopePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ScopePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ScopePo::getId, id);
        return scopeMapper.update(po, wrapper) >= 1;
    }
}

