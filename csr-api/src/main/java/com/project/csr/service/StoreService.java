
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.StorePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.StoreVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-06
 */
public interface StoreService extends IService<StorePo> {

    /**
     * 分页查询
     *
     * @param storeVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.StorePo>
     * @author bin.tong
     * @since 2020-11-06
     */
    IPage<StorePo> findListByPage(StoreVo storeVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-06
     */
    boolean prohibitById(String id);
}