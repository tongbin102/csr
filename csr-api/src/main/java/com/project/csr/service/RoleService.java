
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RolePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.RoleVo;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface RoleService extends IService<RolePo> {

    /**
     * 分页查询
     *
     * @param roleVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RolePo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<RolePo> findListByPage(RoleVo roleVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-10-30
     */
    boolean prohibitById(String id);
}