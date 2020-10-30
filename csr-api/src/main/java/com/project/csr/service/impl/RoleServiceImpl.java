package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.RoleMapper;
import com.project.csr.model.po.RolePo;
import com.project.csr.model.vo.RoleVo;
import com.project.csr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<RolePo> findListByPage(RoleVo roleVo) {
        IPage<RolePo> page = new Page<>(roleVo.getPageNo(), roleVo.getPageSize());
        LambdaQueryWrapper<RolePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<RolePo> selectPage = roleMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        RolePo po = new RolePo();
        po.setValidInd(false);
        LambdaQueryWrapper<RolePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePo::getId, id);
        return roleMapper.update(po, wrapper) >= 1;
    }
}

