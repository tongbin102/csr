package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.SpecificMapper;
import com.project.csr.model.po.SpecificPo;
import com.project.csr.model.vo.SpecificVo;
import com.project.csr.service.SpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 细则 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
@Service
public class SpecificServiceImpl extends ServiceImpl<SpecificMapper, SpecificPo> implements SpecificService {

    @Autowired
    private SpecificMapper specificMapper;

    @Override
    public IPage<SpecificPo> findListByPage(SpecificVo specificVo) {
        IPage<SpecificPo> page = new Page<>(specificVo.getPageNo(), specificVo.getPageSize());
        LambdaQueryWrapper<SpecificPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<SpecificPo> selectPage = specificMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        SpecificPo po = new SpecificPo();
        po.setValidInd(false);
        LambdaQueryWrapper<SpecificPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SpecificPo::getId, id);
        return specificMapper.update(po, wrapper) >= 1;
    }
}

