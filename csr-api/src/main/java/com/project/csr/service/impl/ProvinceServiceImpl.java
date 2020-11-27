package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.ProvinceMapper;
import com.project.csr.model.po.ProvincePo;
import com.project.csr.model.vo.ProvinceVo;
import com.project.csr.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省份表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, ProvincePo> implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public IPage<ProvincePo> findListByPage(ProvinceVo provinceVo) {
        IPage<ProvincePo> page = new Page<>(provinceVo.getPageNo(), provinceVo.getPageSize());
        LambdaQueryWrapper<ProvincePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<ProvincePo> selectPage = provinceMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ProvincePo po = new ProvincePo();
        po.setValidInd(false);
        LambdaQueryWrapper<ProvincePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ProvincePo::getId, id);
        return provinceMapper.update(po, wrapper) >= 1;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<ProvincePo> wrapper = Wrappers.lambdaQuery();
        return provinceMapper.delete(wrapper) >= 1;
    }
}

