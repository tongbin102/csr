package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.dao.UserStoreMapper;
import com.project.csr.model.po.UserStorePo;
import com.project.csr.model.vo.UserStoreVo;
import com.project.csr.service.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-区域/店关系表 服务实现类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-24
 */
@Service
public class UserStoreServiceImpl extends ServiceImpl<UserStoreMapper, UserStorePo> implements UserStoreService {

    @Autowired
    private UserStoreMapper userStoreMapper;

    @Override
    public IPage<UserStorePo> findListByPage(UserStoreVo userStoreVo) {
        IPage<UserStorePo> page = new Page<>(userStoreVo.getPageNo(), userStoreVo.getPageSize());
        LambdaQueryWrapper<UserStorePo> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<UserStorePo> selectPage = userStoreMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        UserStorePo po = new UserStorePo();
        po.setValidInd(false);
        LambdaQueryWrapper<UserStorePo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserStorePo::getId, id);
        return userStoreMapper.update(po, wrapper) >= 1;
    }

    @Override
    public boolean deleteAll() {
        LambdaQueryWrapper<UserStorePo> wrapper = Wrappers.lambdaQuery();
        return userStoreMapper.delete(wrapper) >= 1;
    }
}

