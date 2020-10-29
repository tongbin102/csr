
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.TestPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.TestVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-29
 */
public interface TestService extends IService<TestPo> {

    /**
     * 分页查询
     *
     * @param testVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.TestPo>
     * @author bin.tong
     * @since 2020-10-29
     */
    IPage<TestPo> findListByPage(TestVo testVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-10-29
     */
    boolean prohibitById(String id);
}