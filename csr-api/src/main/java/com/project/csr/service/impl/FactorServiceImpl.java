package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.FactorMapper;
import com.project.csr.model.po.FactorPo;
import com.project.csr.model.vo.FactorVo;
import com.project.csr.service.FactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 因子表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class FactorServiceImpl extends ServiceImpl<FactorMapper, FactorPo> implements FactorService {

    @Autowired
    private FactorMapper factorMapper;

    @Override
    public IPage<FactorPo> findListByPage(FactorVo factorVo) {
        IPage<FactorPo> page = new Page<>(factorVo.getPageNo(), factorVo.getPageSize());
        LambdaQueryWrapper<FactorPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<FactorPo> selectPage = factorMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        FactorPo po = new FactorPo();
        po.setValidInd(false);
        LambdaQueryWrapper<FactorPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(FactorPo::getId, id);
        return factorMapper.update(po, wrapper) >= 1;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<FactorPo> wrapper = Wrappers.lambdaQuery();
        return factorMapper.delete(wrapper) >= 1;
    }
}

