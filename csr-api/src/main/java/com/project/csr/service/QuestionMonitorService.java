
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.QuestionMonitorPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.QuestionMonitorVo;

/**
 * <p>
 * TSS-3 过程监控要求 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface QuestionMonitorService extends IService<QuestionMonitorPo> {

    /**
     * 分页查询
     *
     * @param questionMonitorVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.QuestionMonitorPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<QuestionMonitorPo> findListByPage(QuestionMonitorVo questionMonitorVo);

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