package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ElementMapper;
import com.project.csr.model.po.ElementPo;
import com.project.csr.model.vo.ElementVo;
import com.project.csr.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 因子要素表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
@Service
public class ElementServiceImpl extends ServiceImpl<ElementMapper, ElementPo> implements ElementService {

    @Autowired
    private ElementMapper elementMapper;

    @Override
    public IPage<ElementPo> findListByPage(ElementVo elementVo) {
        IPage<ElementPo> page = new Page<>(elementVo.getPageNo(), elementVo.getPageSize());
        LambdaQueryWrapper<ElementPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ElementPo> selectPage = elementMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ElementPo po = new ElementPo();
        po.setValidInd(false);
        LambdaQueryWrapper<ElementPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ElementPo::getId, id);
        return elementMapper.update(po, wrapper) >= 1;
    }
}

