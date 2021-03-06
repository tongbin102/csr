
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScopePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ScopeVo;

/**
 * <p>
 * 范围表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ScopeService extends IService<ScopePo> {

    /**
     * 分页查询
     *
     * @param scopeVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScopePo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ScopePo> findListByPage(ScopeVo scopeVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-05
     */
    boolean prohibitById(String id);
}