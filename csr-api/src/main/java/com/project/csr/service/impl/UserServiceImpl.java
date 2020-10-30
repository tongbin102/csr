package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.UserMapper;
import com.project.csr.model.po.UserPo;
import com.project.csr.model.vo.UserVo;
import com.project.csr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<UserPo> findListByPage(UserVo userVo) {
        IPage<UserPo> page = new Page<>(userVo.getPageNo(), userVo.getPageSize());
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<UserPo> selectPage = userMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        UserPo po = new UserPo();
        po.setValidInd(false);
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getId, id);
        return userMapper.update(po, wrapper) >= 1;
    }
}

