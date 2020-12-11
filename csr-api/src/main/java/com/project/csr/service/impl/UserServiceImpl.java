package com.project.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.csr.constants.CsrConstant;
import com.project.csr.constants.DictionaryType;
import com.project.csr.dao.UserMapper;
import com.project.csr.model.po.RolePo;
import com.project.csr.model.po.UserPo;
import com.project.csr.model.vo.UserVo;
import com.project.csr.service.RoleService;
import com.project.csr.service.UserService;
import com.project.csr.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder encoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

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

    @Override
    public boolean prohibitAllExceptAdmin() {
        UserPo po = new UserPo();
        po.setValidInd(false);
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(UserPo::getUsername, "admin");
        return userMapper.update(po, wrapper) >= 1;
    }

    @Override
    public UserVo findByUsername(String username) {
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getUsername, username);
        UserPo userPo = userMapper.selectOne(wrapper);
        RolePo rolePo = null;
        if (null != userPo.getRoleId()) {
            rolePo = roleService.getById(userPo.getRoleId());
        }
        UserVo userVo = ConvertUtils.convert(userPo, UserVo.class);
        userVo.setRoleName(rolePo.getName());
        return userVo;
    }

    @Override
    public boolean deleteUsersExceptAdmin() {
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(UserPo::getUsername, "admin");
        return userMapper.delete(wrapper) >= 1;
    }

    @Override
    public boolean resetPassword(String username) {
        UserPo po = new UserPo();
        po.setPassword(encoder.encode(CsrConstant.DEFAULT_RAW_PASSWORD));
        po.setChangeFlag(DictionaryType.CHANGE_PASSWORD_NEED);

        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getUsername, username);
        return userMapper.update(po, wrapper) >= 1;
    }

    @Override
    public boolean changePassword(String username, String password) {
        UserPo po = new UserPo();
        po.setPassword(encoder.encode(password));
        po.setChangeFlag(DictionaryType.CHANGE_PASSWORD_DONT_NEED);
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getUsername, username);
        return userMapper.update(po, wrapper) >= 1;

    }
}

