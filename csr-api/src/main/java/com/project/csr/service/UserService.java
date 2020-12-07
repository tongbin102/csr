
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.UserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface UserService extends IService<UserPo> {

    /**
     * 分页查询
     *
     * @param userVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.UserPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<UserPo> findListByPage(UserVo userVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-10-30
     */
    boolean prohibitById(String id);

    /**
     * 禁用所有数据
     *
     * @return
     */
    boolean prohibitAllExceptAdmin();

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    UserVo findByUsername(String username);

    /**
     * 删除除Admin以外的用户
     */
    boolean deleteUsersExceptAdmin();

    /**
     * 重置密码
     *
     * @param username
     * @return
     */
    boolean resetPassword(String username);

    /**
     * 修改密码
     *
     * @param username
     * @param password
     * @return
     */
    boolean changePassword(String username, String password);
}