
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.UserStorePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.UserStoreVo;

import java.util.List;

/**
 * <p>
 * 用户-区域/店关系表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-24
 */
public interface UserStoreService extends IService<UserStorePo> {

    /**
     * 分页查询
     *
     * @param userStoreVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.UserStorePo>
     * @author bin.tong
     * @since 2020-11-24
     */
    IPage<UserStorePo> findListByPage(UserStoreVo userStoreVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-24
     */
    boolean prohibitById(String id);

    /**
     * 删除所有数据
     */
    boolean deleteAll();

    /**
     * 通过用户code获取Vo数据
     *
     * @param userCode
     * @return
     */
    List<UserStoreVo> findVoByUserCode(String userCode);
}