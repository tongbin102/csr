package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RegulationMapper;
import com.project.csr.model.po.RegulationPo;
import com.project.csr.model.vo.RegulationVo;
import com.project.csr.service.ElementService;
import com.project.csr.service.RegulationService;
import com.project.csr.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 细则表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-11
 */
@Service
public class RegulationServiceImpl extends ServiceImpl<RegulationMapper, RegulationPo> implements RegulationService {

    @Autowired
    private RegulationMapper regulationMapper;

    @Autowired
    private ElementService elementService;

    @Override
    public IPage<RegulationPo> findListByPage(RegulationVo regulationVo) {
        IPage<RegulationPo> page = new Page<>(regulationVo.getPageNo(), regulationVo.getPageSize());
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RegulationPo> selectPage = regulationMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RegulationPo po = new RegulationPo();
        po.setValidInd(false);
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RegulationPo::getId, id);
        return regulationMapper.update(po, wrapper) >= 1;
    }

    @Override
    public List<RegulationPo> findListFromIds(String ids, String delimiter) {
        LambdaQueryWrapper<RegulationPo> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RegulationPo::getId, ids.split(delimiter));
        return regulationMapper.selectList(wrapper);
    }

    @Override
    public RegulationVo findVoById(Long id) {
        RegulationVo regulationVo = ConvertUtils.convert(this.getById(id), RegulationVo.class);
        regulationVo.setElementPo(elementService.getById(regulationVo.getElementId()));
        return regulationVo;
    }
}

