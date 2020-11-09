
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.SpecificPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.SpecificVo;

/**
 * <p>
 * 细则 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-11-09
 */
public interface SpecificService extends IService<SpecificPo> {

    /**
     * 分页查询
     *
     * @param specificVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.SpecificPo>
     * @author bin.tong
     * @since 2020-11-09
     */
    IPage<SpecificPo> findListByPage(SpecificVo specificVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-11-09
     */
    boolean prohibitById(String id);
}