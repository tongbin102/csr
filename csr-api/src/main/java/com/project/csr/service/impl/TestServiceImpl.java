package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.TestMapper;
import com.project.csr.model.po.TestPo;
import com.project.csr.model.vo.TestVo;
import com.project.csr.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-29
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestPo> implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public IPage<TestPo> findListByPage(TestVo testVo) {
        IPage<TestPo> page = new Page<>(testVo.getPageNo(), testVo.getPageSize());
        LambdaQueryWrapper<TestPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<TestPo> selectPage = testMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        TestPo po = new TestPo();
        po.setValidInd(false);
        LambdaQueryWrapper<TestPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TestPo::getId, id);
        return testMapper.update(po, wrapper) >= 1;
    }
}

