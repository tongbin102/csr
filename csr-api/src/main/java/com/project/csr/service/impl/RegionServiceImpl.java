package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RegionMapper;
import com.project.csr.model.po.RegionPo;
import com.project.csr.model.vo.RegionVo;
import com.project.csr.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 大区表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionPo> implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public IPage<RegionPo> findListByPage(RegionVo regionVo) {
        IPage<RegionPo> page = new Page<>(regionVo.getPageNo(), regionVo.getPageSize());
        LambdaQueryWrapper<RegionPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RegionPo> selectPage = regionMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RegionPo po = new RegionPo();
        po.setValidInd(false);
        LambdaQueryWrapper<RegionPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegionPo::getId, id);
        return regionMapper.update(po, wrapper) >= 1;
    }
}

