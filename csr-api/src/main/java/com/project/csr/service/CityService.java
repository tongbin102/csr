
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.CityPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.CityVo;

/**
 * <p>
 * 城市表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-13
 */
public interface CityService extends IService<CityPo> {

    /**
     * 分页查询
     *
     * @param cityVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.CityPo>
     * @author bin.tong
     * @since 2020-11-13
     */
    IPage<CityPo> findListByPage(CityVo cityVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-13
     */
    boolean prohibitById(String id);
}