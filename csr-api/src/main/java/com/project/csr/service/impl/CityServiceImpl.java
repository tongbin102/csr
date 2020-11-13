package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.CityMapper;
import com.project.csr.model.po.CityPo;
import com.project.csr.model.vo.CityVo;
import com.project.csr.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, CityPo> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public IPage<CityPo> findListByPage(CityVo cityVo) {
        IPage<CityPo> page = new Page<>(cityVo.getPageNo(), cityVo.getPageSize());
        LambdaQueryWrapper<CityPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<CityPo> selectPage = cityMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        CityPo po = new CityPo();
        po.setValidInd(false);
        LambdaQueryWrapper<CityPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CityPo::getId, id);
        return cityMapper.update(po, wrapper) >= 1;
    }
}

