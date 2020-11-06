package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.StoreMapper;
import com.project.csr.model.po.StorePo;
import com.project.csr.model.vo.StoreVo;
import com.project.csr.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-06
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, StorePo> implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public IPage<StorePo> findListByPage(StoreVo storeVo) {
        IPage<StorePo> page = new Page<>(storeVo.getPageNo(), storeVo.getPageSize());
        LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<StorePo> selectPage = storeMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        StorePo po = new StorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<StorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StorePo::getId, id);
        return storeMapper.update(po, wrapper) >= 1;
    }
}

