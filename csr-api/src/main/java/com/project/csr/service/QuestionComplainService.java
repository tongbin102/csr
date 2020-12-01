
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionComplainPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.QuestionComplainVo;

/**
 * <p>
 * 投诉单表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-12-01
 */
public interface QuestionComplainService extends IService<QuestionComplainPo> {

    /**
     * 分页查询
     *
     * @param questionComplainVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionComplainPo>
     * @author bin.tong
     * @since 2020-12-01
     */
    IPage<QuestionComplainPo> findListByPage(QuestionComplainVo questionComplainVo);

    /**
     * 根据ID禁用数据
     *
     * @param id 主键id
     * @return boolean
     * @author bin.tong
     * @since 2020-12-01
     */
    boolean prohibitById(String id);

    /**
     * 通过期数删除数据
     *
     * @return boolean
     */
    boolean deleteByPeriod(String period);
}