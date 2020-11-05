
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ElementPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ElementVo;

/**
 * <p>
 * 因子要素表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-05
 */
public interface ElementService extends IService<ElementPo> {

    /**
     * 分页查询
     *
     * @param elementVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ElementPo>
     * @author bin.tong
     * @since 2020-11-05
     */
    IPage<ElementPo> findListByPage(ElementVo elementVo);

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