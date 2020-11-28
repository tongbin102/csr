
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.RegionPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.RegionVo;

/**
 * <p>
 * 大区表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
public interface RegionService extends IService<RegionPo> {

    /**
     * 分页查询
     *
     * @param regionVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.RegionPo>
     * @author bin.tong
     * @since 2020-11-13
     */
    IPage<RegionPo> findListByPage(RegionVo regionVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-13
     */
    boolean prohibitById(String id);

    /**
     * 删除所有数据
     */
    boolean deleteAll();

}