
package com.project.csr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.csr.model.po.ScoreQuestionPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.csr.model.vo.ScoreQuestionVo;

/**
 * <p>
 * 分项成绩统计表 服务类
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
public interface ScoreQuestionService extends IService<ScoreQuestionPo> {

    /**
     * 分页查询
     *
     * @param scoreQuestionVo 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.ghtg.csr.pojo.po.ScoreQuestionPo>
     * @author bin.tong
     * @since 2020-10-30
     */
    IPage<ScoreQuestionPo> findListByPage(ScoreQuestionVo scoreQuestionVo);

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